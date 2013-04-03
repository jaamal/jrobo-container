package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.annotations.ContainerConstructor;
import overclocking.jrobocontainer.exceptions.*;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IStorage;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractConfiguration implements IConfiguration
{
    IStorage storage;
    protected String abstractionId;

    protected <T> T getInstance(String resolvedClassId, IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode resolvedClass = injectionContext.getClassNodesStorage().getClassNodeById(resolvedClassId);
        if (injectionContext.isClassProcessing(resolvedClassId))
            throw new CyclicalDependencyException(resolvedClass);
        injectionContext.markClassAsProcessing(resolvedClassId);
        Constructor<T> constructor = getConstructor(resolvedClassId, injectionContext, classLoader);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++)
        {
            if (parametersTypes[i].isArray())
            {
                String classId = injectionContext.getClassNodesStorage().getClassId(parametersTypes[i].getComponentType());
                ClassLoader nextClassLoader = injectionContext.getClassNodesStorage().getClassNodeById(classId).getClassLoader();
                if(nextClassLoader == null)
                    nextClassLoader = classLoader;
                parameters[i] = storage.getConfiguration(classId).getAll(injectionContext, nextClassLoader);
            }
            else
            {
                String classId = injectionContext.getClassNodesStorage().getClassId(parametersTypes[i]);
                ClassLoader nextClassLoader = injectionContext.getClassNodesStorage().getClassNodeById(classId).getClassLoader();
                if(nextClassLoader == null)
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
            throw new JRoboContainerException("Failed to get " + resolvedClass.getClassName(), injectionContext, ex);
        }
    }

    private <T> Constructor<T> getConstructor(String classNodeId, IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode classNode = injectionContext.getClassNodesStorage().getClassNodeById(classNodeId);
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
        if(result == null)
        {
            StringBuilder res = new StringBuilder();
            for(Constructor<T> constructor : constructors)
                res.append(constructor);
            throw new AmbiguousConstructorException(clazz, res.toString());
        }
        return result;
    }

    abstract protected <T> T innerGet(IInjectionContext injectionContext, ClassLoader classLoader);
    abstract protected <T> T innerCreate(IInjectionContext injectionContext, ClassLoader classLoader);

    @Override
    public <T> T get(IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        injectionContext.beginGet(abstraction);
        T result = innerGet(injectionContext, classLoader);
        injectionContext.endGet(abstraction);
        return result;
    }

    @Override
    public <T> T create(IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        injectionContext.beginCreate(abstraction);
        T result = innerCreate(injectionContext, classLoader);
        injectionContext.endCreate(abstraction);
        return result;
    }

    public <T> T[] getAll(IInjectionContext injectionContext, ClassLoader classLoader)
    {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        abstraction.setClazz(loadClass(classLoader, abstraction.getClassName()));
        injectionContext.beginGetAll(abstraction);
        ArrayList<String> implementations = storage.getImplementations(abstractionId);
        ArrayList<T> result = new ArrayList<T>();
        for (String implementationId : implementations)
            result.add((T) storage.getConfiguration(implementationId).get(injectionContext, classLoader));
        injectionContext.endGetAll(abstraction);
        return result.toArray((T[]) Array.newInstance(abstraction.getClazz(), result.size()));
    }

    public <T> Class<T> loadClass(ClassLoader classLoader, String className)     {
        try
        {
            return (Class<T>)classLoader.loadClass(className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            return null;
        }
    }
}
