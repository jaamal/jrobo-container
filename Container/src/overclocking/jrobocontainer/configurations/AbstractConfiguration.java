package overclocking.jrobocontainer.configurations;

import overclocking.jrobocontainer.annotations.ContainerConstructor;
import overclocking.jrobocontainer.exceptions.AmbiguousConstructorException;
import overclocking.jrobocontainer.exceptions.CyclicalDependencyException;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.exceptions.NoConstructorsFoundException;
import overclocking.jrobocontainer.storage.IStorage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;

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
    protected Class<?> abstraction;

    protected <T> T getInstance(Class<T> resolvedClass, HashSet<Class<?>> usedClasses) throws InvocationTargetException, IllegalAccessException, InstantiationException, JRoboContainerException
    {
        if (usedClasses.contains(resolvedClass))
            throw new CyclicalDependencyException(resolvedClass);
        usedClasses.add(resolvedClass);
        Constructor<T> constructor = getConstructor(resolvedClass);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++)
        {
            if (parametersTypes[i].isArray())
                parameters[i] = storage.getConfiguration(parametersTypes[i].getComponentType()).getAll(usedClasses);
            else
                parameters[i] = storage.getConfiguration(parametersTypes[i]).get(usedClasses);
        }
        usedClasses.remove(resolvedClass);
        return constructor.newInstance(parameters);
    }

    private <T> Constructor<T> getConstructor(Class<T> clazz) throws NoConstructorsFoundException, AmbiguousConstructorException
    {
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
                    throw new AmbiguousConstructorException(clazz);
                result = constructor;
            }
        }
        return result;
    }

    public <T> T[] getAll(HashSet<Class<?>> usedClasses) throws JRoboContainerException
    {
        ArrayList<Class<?>> implementations = storage.getImplementations(abstraction);
        ArrayList<T> result = new ArrayList<T>();
        for (Class<?> implementation : implementations)
            result.add((T) storage.getConfiguration(implementation).get(usedClasses));
        return result.toArray((T[]) Array.newInstance(abstraction, result.size()));
    }
}
