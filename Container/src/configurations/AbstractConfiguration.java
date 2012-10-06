package configurations;

import annotations.ContainerConstructor;
import exceptions.AmbiguousConstructorException;
import exceptions.CyclicalDependencyException;
import exceptions.JRoboContainerException;
import exceptions.NoConstructorsFoundException;
import storage.IStorage;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 06.10.12
 * Time: 16:42
 * To change this template use File | Settings | File Templates.
 */
public abstract class AbstractConfiguration implements IConfiguration {
    IStorage storage;

    protected <T> T getInstance(Class<T> resolvedClass, HashSet<Class<?>> usedClasses) throws InvocationTargetException, IllegalAccessException, InstantiationException, JRoboContainerException {
        if(usedClasses.contains(resolvedClass))
            throw new CyclicalDependencyException(resolvedClass);
        usedClasses.add(resolvedClass);
        Constructor<T> constructor = getConstructor(resolvedClass);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++) {
            parameters[i] = storage.getConfiguration(parametersTypes[i]).get(usedClasses);
        }
        usedClasses.remove(resolvedClass);
        return constructor.newInstance(parameters);
    }

    private <T> Constructor<T> getConstructor(Class<T> clazz) throws NoConstructorsFoundException, AmbiguousConstructorException {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        if (constructors.length == 1)
            return constructors[0];
        if (constructors.length == 0)
            throw new NoConstructorsFoundException(clazz);
        Constructor<T> result = null;
        for (Constructor<T> constructor : constructors) {
            Annotation[] annotations = constructor.getAnnotations();
            if (constructor.getAnnotation(ContainerConstructor.class) != null) {
                if (result != null)
                    throw new AmbiguousConstructorException(clazz);
                result = constructor;
            }
        }
        return result;
    }
}
