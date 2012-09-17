package container;

import annotations.ContainerConstructor;
import classloader.IPathsFilter;
import classloader.JRoboClassLoader;
import classloader.Resolver;
import exceptions.AmbiguousConstructorException;
import exceptions.NoConstructorsFoundException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import storage.IStorage;
import storage.Storage;
import exceptions.JRoboContainerException;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Container implements IContainer {

    private JRoboClassLoader classLoader;
    private IStorage storage;
    private Resolver resolver;

    public Container(IPathsFilter filter) {
        storage = new Storage();
        resolver = new Resolver();
        classLoader = new JRoboClassLoader(storage, System.getProperty("java.class.path"), filter);
        classLoader.loadClasses();
        storage.buildFullDiagram();
        logger.info("Container was configured successfully.");
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T get(Class<T> requiredAbstraction) throws JRoboContainerException {
        try {
            logger.info("Getting " + requiredAbstraction.getName());
            if (storage.hasInstance(requiredAbstraction))
                return (T) storage.getInstance(requiredAbstraction);
            Class<?> resolvedClass = resolveClass(requiredAbstraction);
            logger.info(requiredAbstraction.getName() + " resolved to " + resolvedClass.getName());
            synchronized (storage.getSynchronizeObject(resolvedClass)) {
                if (storage.getInstance(resolvedClass) == null) {
                    storage.putInstance(resolvedClass, getInstance(resolvedClass));
                }
            }
            return (T) storage.getInstance(resolvedClass);
        }
        catch(JRoboContainerException ex)
        {
            throw ex;
        }
        catch(Exception ex)
        {
            throw new JRoboContainerException("Failed to get " + requiredAbstraction.getName(), ex);
        }
    }

    private Class<?> resolveClass(Class<?> requiredAbstraction) throws JRoboContainerException {
        return resolver.resolveClass(requiredAbstraction, storage.getImplementations(requiredAbstraction));
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T create(Class<T> requiredAbstraction) throws JRoboContainerException {
        try {
            logger.info("Creating " + requiredAbstraction.getName());
            Class<?> resolvedClass = resolveClass(requiredAbstraction);
            return (T) getInstance(resolvedClass);
        }
        catch(JRoboContainerException ex)
        {
            throw ex;
        }
        catch(Exception ex) {
            ex.printStackTrace();
            throw new JRoboContainerException("Failed to create " + requiredAbstraction.getName(), ex);
        }
    }

    @Override
    public <T1, T2 extends T1> void bind(Class<T1> abstraction, T2 instance) {
        storage.bindInstance(abstraction, instance);
    }


    private <T> T getInstance(Class<T> resolvedClass) throws InvocationTargetException, IllegalAccessException, InstantiationException, JRoboContainerException {

        Constructor<T> constructor = getConstructor(resolvedClass);
        Class<?>[] parametersTypes = constructor.getParameterTypes();
        int parametersCount = constructor.getParameterTypes().length;
        Object parameters[] = new Object[parametersCount];
        for (int i = 0; i < parametersCount; i++) {
            parameters[i] = get(parametersTypes[i]);
        }
        return constructor.newInstance(parameters);
    }

    private <T> Constructor<T> getConstructor(Class<T> clazz) throws NoConstructorsFoundException, AmbiguousConstructorException {
        Constructor<T>[] constructors = (Constructor<T>[]) clazz.getConstructors();
        if(constructors.length == 1)
            return constructors[0];
        if(constructors.length == 0)
            throw new NoConstructorsFoundException(clazz);
        Constructor<T> result = null;
        for(Constructor<T> constructor : constructors)
        {
            Annotation[] annotations = constructor.getAnnotations();
            if(constructor.getAnnotation(ContainerConstructor.class) != null)
            {
                if(result != null)
                    throw new AmbiguousConstructorException(clazz);
                result = constructor;
            }
        }
        return result;
    }

    Logger logger = LogManager.getLogger(Container.class);
}
