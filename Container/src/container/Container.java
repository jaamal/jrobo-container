package container;

import classloader.IPathsFilter;
import classloader.JRoboClassLoader;
import classloader.Resolver;
import storage.IStorage;
import storage.Storage;
import exceptions.JRoboContainerException;

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
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> requiredAbstraction) throws JRoboContainerException {
        Class<?> resolvedClass = resolveClass(requiredAbstraction);
		if(storage.getInstance(resolvedClass) == null)
			storage.putInstance(resolvedClass, resolver.createNewInstance(resolvedClass));
		return (T)storage.getInstance(resolvedClass);
	}

    private Class<?> resolveClass(Class<?> requiredAbstraction) throws JRoboContainerException {
        return resolver.resolveClass(requiredAbstraction, storage.getImplementations(requiredAbstraction));
    }

    @SuppressWarnings("unchecked")
	@Override
	public <T> T create(Class<T> requiredAbstraction) throws JRoboContainerException {
		Class<?> resolvedClass = resolveClass(requiredAbstraction);
		return (T)resolver.createNewInstance(resolvedClass);
	}
}
