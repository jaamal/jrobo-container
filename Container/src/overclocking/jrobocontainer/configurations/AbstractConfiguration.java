package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.annotations.ContainerConstructor;
import overclocking.jrobocontainer.exceptions.*;
import overclocking.jrobocontainer.injectioncontext.IInjectionContext;
import overclocking.jrobocontainer.storages.ClassNode;
import overclocking.jrobocontainer.storages.IStorage;

import java.lang.annotation.Annotation;
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

    protected <T> T getInstance(String resolvedClassId, IInjectionContext injectionContext)
    {
        ClassNode resolvedClass = injectionContext.getClassNodesStorage().getClassNodeById(resolvedClassId);
        if (injectionContext.isClassProcessing(resolvedClassId))
            throw new CyclicalDependencyException(resolvedClass);
        injectionContext.markClassAsProcessing(resolvedClassId);
        Constructor<T> constructor = getConstructor(resolvedClassId, injectionContext);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++)
        {
            if (parametersTypes[i].isArray())
                parameters[i] = storage.getConfiguration(injectionContext.getClassNodesStorage().getClassId(parametersTypes[i].getComponentType())).getAll(injectionContext);
            else
                parameters[i] = storage.getConfiguration(injectionContext.getClassNodesStorage().getClassId(parametersTypes[i])).get(injectionContext);
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

    private <T> Constructor<T> getConstructor(String classNodeId, IInjectionContext injectionContext)
    {
        Class<T> clazz = injectionContext.getClassNodesStorage().getClassById(classNodeId, injectionContext.getClassLoadersStorage());
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        if (constructors.length == 1)
            return constructors[0];
        if (constructors.length == 0)
            throw new NoConstructorsFoundException(clazz);
        Constructor<T> result = null;
        for (Constructor<T> constructor : constructors)
        {
            Annotation[] annotations = constructor.getAnnotations();
            if (constructor.getAnnotation(ContainerConstructor.class) != null)
            {
                if (result != null)
                    throw new AmbiguousAnnotatedConstructorException(clazz);
                result = constructor;
            }
        }
        if(result == null)
            throw new AmbiguousConstructorException(clazz);
        return result;
    }

    abstract protected <T> T innerGet(IInjectionContext injectionContext);
    abstract protected <T> T innerCreate(IInjectionContext injectionContext);

    @Override
    public <T> T get(IInjectionContext injectionContext)
    {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        injectionContext.beginGet(abstraction);
        T result = innerGet(injectionContext);
        injectionContext.endGet(abstraction);
        return result;
    }

    @Override
    public <T> T create(IInjectionContext injectionContext)
    {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        injectionContext.beginCreate(abstraction);
        T result = innerCreate(injectionContext);
        injectionContext.endCreate(abstraction);
        return result;
    }

    public <T> T[] getAll(IInjectionContext injectionContext)
    {
        ClassNode abstraction = injectionContext.getClassNodesStorage().getClassNodeById(abstractionId);
        abstraction.setClazz(injectionContext.getClassLoadersStorage().getClassLoaderFor(abstraction.getClassName()));
        injectionContext.beginGetAll(abstraction);
        ArrayList<String> implementations = storage.getImplementations(abstractionId);
        ArrayList<T> result = new ArrayList<T>();
        for (String implementationId : implementations)
            result.add((T) storage.getConfiguration(implementationId).get(injectionContext));
        injectionContext.endGetAll(abstraction);
        return result.toArray((T[]) Array.newInstance(abstraction.getClazz(), result.size()));
    }
}
