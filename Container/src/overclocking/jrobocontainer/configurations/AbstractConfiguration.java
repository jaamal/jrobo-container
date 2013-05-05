package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.annotations.ContainerConstructor;
import overclocking.jrobocontainer.container.AbstractionInstancePair;
import overclocking.jrobocontainer.exceptions.*;
import overclocking.jrobocontainer.factories.FactoryCreator;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IClassNodesStorage;
import overclocking.jrobocontainer.storages.IStorage;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Arrays;

public abstract class AbstractConfiguration implements IConfiguration
{
    protected IStorage storage;
    protected IClassNodesStorage classNodesStorage;
    protected String abstractionId;
    protected FactoryCreator factoryCreator;

    protected AbstractConfiguration(IStorage storage, IClassNodesStorage classNodesStorage)
    {
        this.storage = storage;
        this.classNodesStorage = classNodesStorage;
        factoryCreator = new FactoryCreator();
    }

    protected <T> T getInstance(String resolvedClassId, IInjectionContext injectionContext, ClassLoader classLoader, AbstractionInstancePair[] substitutions)
    {
        if (substitutions == null)
            substitutions = new AbstractionInstancePair[0];
        boolean isSubstitutionUsed[] = new boolean[substitutions.length];
        Arrays.fill(isSubstitutionUsed, false);
        ClassNode resolvedClass = classNodesStorage.getClassNodeById(resolvedClassId);
        if (injectionContext.isClassProcessing(resolvedClassId))
            throw new CyclicalDependencyException(resolvedClass);
        injectionContext.markClassAsProcessing(resolvedClassId);
        Constructor<T> constructor = getConstructor(resolvedClassId, injectionContext, classLoader, substitutions);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++)
        {
            parameters[i] = null;
            for (int j = 0; j < substitutions.length; j++)
                if (!isSubstitutionUsed[j] && parametersTypes[i].isAssignableFrom(substitutions[j].getAbstraction()))
                {
                    parameters[i] = substitutions[j].getInstance();
                    isSubstitutionUsed[j] = true;
                    break;
                }
            if (parameters[i] != null)
                continue;
            if (parametersTypes[i].isArray())
            {
                String classId = classNodesStorage.getClassId(parametersTypes[i].getComponentType());
                ClassLoader nextClassLoader = classNodesStorage.getClassNodeById(classId).getClassLoader();
                if (nextClassLoader == null)
                    nextClassLoader = classLoader;
                parameters[i] = storage.getConfiguration(classId).getAll(injectionContext, nextClassLoader);
            } else
            {
                String classId = classNodesStorage.getClassId(parametersTypes[i]);
                ClassLoader nextClassLoader = classNodesStorage.getClassNodeById(classId).getClassLoader();
                if (nextClassLoader == null)
                    nextClassLoader = classLoader;
                parameters[i] = storage.getConfiguration(classId).get(injectionContext, nextClassLoader);
            }
        }
        injectionContext.markClassAsNotProcessing(resolvedClassId);
        try
        {
            return constructor.newInstance(parameters);
        }
        catch (Exception ex)
        {
            throw new JroboContainerException("Failed to get " + resolvedClass.getClassName(), injectionContext, ex);
        }
    }

    private <T> Constructor<T> getConstructor(String classNodeId, IInjectionContext injectionContext, ClassLoader classLoader, AbstractionInstancePair[] substitutions)
    {
        ClassNode classNode = classNodesStorage.getClassNodeById(classNodeId);
        Class<T> clazz = loadClass(classLoader, classNode.getClassName());
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        if (constructors.length == 1)
            return constructors[0];
        if (constructors.length == 0)
            throw new NoConstructorsFoundException(clazz);
        Constructor<T> result = null;
        for (Constructor<T> constructor : constructors)
        {
            if (constructor.getAnnotation(ContainerConstructor.class) != null)
            {
                if (result != null)
                    throw new AmbiguousAnnotatedConstructorException(clazz);
                result = constructor;
            }
        }
        if (result != null)
            return result;
        for (Constructor constructor : constructors)
        {
            Class[] types = constructor.getParameterTypes();
            if (types.length != substitutions.length)
                continue;
            boolean fail = false;
            for (int i = 0; i < types.length; i++)
                if (!substitutions[i].getAbstraction().isAssignableFrom(types[i]))
                    fail = true;
            if (!fail)
                return constructor;
        }
        if (constructors.length == 2)
        {
            if (constructors[0].getParameterTypes().length == 0)
                return constructors[1];
            if (constructors[1].getParameterTypes().length == 0)
                return constructors[0];
        }
        StringBuilder res = new StringBuilder();
        for (Constructor<T> constructor : constructors)
            res.append(constructor);
        throw new AmbiguousConstructorException(clazz, res.toString());
    }

    abstract protected <T> T innerGet(IInjectionContext injectionContext, ClassLoader classLoader);

    abstract protected <T> T innerCreate(IInjectionContext injectionContext, ClassLoader classLoader, AbstractionInstancePair[] substitutions);

    @Override
    public <T> T get(IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode abstraction = classNodesStorage.getClassNodeById(abstractionId);
        abstraction.setClazz(loadClass(classLoader, abstraction.getClassName()));
        injectionContext.beginGet(abstraction);
        T result;
        result = innerGet(injectionContext, classLoader);
        injectionContext.endGet(abstraction);
        return result;
    }

    @Override
    public <T> T create(IInjectionContext injectionContext, ClassLoader classLoader, AbstractionInstancePair[] substitutions)
    {
        ClassNode abstraction = classNodesStorage.getClassNodeById(abstractionId);
        abstraction.setClazz(loadClass(classLoader, abstraction.getClassName()));
        injectionContext.beginCreate(abstraction);
        T result;
        result = innerCreate(injectionContext, classLoader, substitutions);
        injectionContext.endCreate(abstraction);
        return result;
    }

    public <T> T[] getAll(IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode abstraction = classNodesStorage.getClassNodeById(abstractionId);
        abstraction.setClazz(loadClass(classLoader, abstraction.getClassName()));
        injectionContext.beginGetAll(abstraction);
        ArrayList<String> implementations = storage.getImplementations(abstractionId);
        ArrayList<T> result = new ArrayList<T>();
        for (String implementationId : implementations)
            result.add((T) storage.getConfiguration(implementationId).get(injectionContext, classLoader));
        injectionContext.endGetAll(abstraction);
        return result.toArray((T[]) Array.newInstance(abstraction.getClazz(), result.size()));
    }

    public <T> Class<T> loadClass(ClassLoader classLoader, String className)
    {
        try
        {
            return (Class<T>) classLoader.loadClass(className);
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }


    protected String resolveClass(String requiredAbstractionId,
                                  ArrayList<String> implementations, IClassNodesStorage classNodesStorage)
    {
        ClassNode requiredAbstraction = classNodesStorage.getClassNodeById(requiredAbstractionId);
        if (implementations == null || implementations.size() == 0)
        {
            throw new ImplementationNotFoundException(requiredAbstraction);
        }
        if (implementations.size() > 1)
        {
            String implementationsString = "";
            for (String implementation : implementations)
                implementationsString = implementationsString + classNodesStorage.getClassNodeById(implementation).getClassName() + System.getProperty("line.separator");
            throw new AmbiguousImplementationMatchException(requiredAbstraction, implementationsString);
        }
        return implementations.get(0);
    }
}
