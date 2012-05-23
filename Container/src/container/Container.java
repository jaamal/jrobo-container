package container;

import classloader.IPathsFilter;
import classloader.JRoboClassLoader;
import classloader.Resolver;
import exceptions.JRoboContainerException;
import storage.IStorage;
import storage.Storage;

public class Container implements IContainer {

	private JRoboClassLoader classLoader;
	private IStorage storage;
	private Resolver resolver;
    private IPathsFilter filter;

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
		Class<?> resolvedClass = resolver.resolveClass(requiredAbstraction, storage.getImplementations(requiredAbstraction));
		if(storage.getInstance(resolvedClass) == null)
			storage.putInstance(resolvedClass, resolver.createNewInstance(resolvedClass));
		return (T)storage.getInstance(resolvedClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(Class<T> requiredAbstraction) throws JRoboContainerException {
		Class<?> resolvedClass = resolver.resolveClass(requiredAbstraction, storage.getImplementations(requiredAbstraction));
		return (T)resolver.createNewInstance(resolvedClass);
	}
}
