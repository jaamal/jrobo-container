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
    protected ClassNode abstraction;

    protected <T> T getInstance(ClassNode resolvedClass, IInjectionContext injectionContext)
    {
        if (injectionContext.isClassProcessing(resolvedClass))
            throw new CyclicalDependencyException(resolvedClass);
        injectionContext.markClassAsProcessing(resolvedClass);
        Constructor<T> constructor = getConstructor(resolvedClass, injectionContext);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++)
        {
            if (parametersTypes[i].isArray())
                parameters[i] = storage.getConfiguration(injectionContext.getClassNodesStorage().getClassNode(parametersTypes[i].getComponentType())).getAll(injectionContext);
            else
                parameters[i] = storage.getConfiguration(injectionContext.getClassNodesStorage().getClassNode(parametersTypes[i])).get(injectionContext);
        }
        injectionContext.markClassAsNotProcessing(resolvedClass);
        try
        {
            return constructor.newInstance(parameters);
        }
        catch (Exception ex)
        {
            throw new JRoboContainerException("Failed to get " + resolvedClass.getClassName(), injectionContext, ex);
        }
    }

    private <T> Constructor<T> getConstructor(ClassNode classNode, IInjectionContext injectionContext)
    {
        Class<T> clazz = injectionContext.getClassNodesStorage().getClassByNode(classNode, injectionContext.getClassLoadersStorage());
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
        injectionContext.beginGet(abstraction);
        T result = innerGet(injectionContext);
        injectionContext.endGet(abstraction);
        return result;
    }

    @Override
    public <T> T create(IInjectionContext injectionContext)
    {
        injectionContext.beginCreate(abstraction);
        T result = innerCreate(injectionContext);
        injectionContext.endCreate(abstraction);
        return result;
    }

    public <T> T[] getAll(IInjectionContext injectionContext)
    {
        injectionContext.beginGetAll(abstraction);
        ArrayList<ClassNode> implementations = storage.getImplementations(abstraction);
        ArrayList<T> result = new ArrayList<T>();
        for (ClassNode implementation : implementations)
            result.add((T) storage.getConfiguration(implementation).get(injectionContext));
        injectionContext.endGetAll(abstraction);
        return result.toArray((T[]) Array.newInstance(abstraction.getClazz(), result.size()));
    }
}
