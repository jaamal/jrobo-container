package configurations;

import annotations.ContainerConstructor;
import classloader.Resolver;
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
 * Time: 13:38
 * To change this template use File | Settings | File Templates.
 */
public class AutoConfiguration extends AbstractConfiguration {

    private Object instance;

    public AutoConfiguration(IStorage storage, Class<?> abstraction) {
        this.storage = storage;
        this.abstraction = abstraction;
        instance = null;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(HashSet<Class<?>> usedClasses) throws JRoboContainerException {
        try {
            if(instance != null)
            {
                return (T)instance;
            }
            Class<?> resolvedClass = Resolver.resolveClass(abstraction, storage.getImplementations(abstraction));
            synchronized (storage.getSynchronizeObject(resolvedClass)) {
                if(resolvedClass == abstraction)
                    instance = getInstance(resolvedClass, usedClasses);
                else
                    instance = storage.getConfiguration(resolvedClass).get(usedClasses);
            }
            return (T)instance;
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            throw new JRoboContainerException("Failed to get " + abstraction.getName(), ex);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T create(HashSet<Class<?>> usedClasses) throws JRoboContainerException {
        try {
            Class<?> resolvedClass = Resolver.resolveClass(abstraction, storage.getImplementations(abstraction));
            return (T)getInstance(resolvedClass, usedClasses);
        } catch (JRoboContainerException ex) {
            throw ex;
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new JRoboContainerException("Failed to create " + abstraction.getName(), ex);
        }
    }
}
