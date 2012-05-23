package container;

import classloader.JRoboClassLoader;
import classloader.Resolver;
import storage.IStorage;
import storage.Storage;
import exceptions.JRoboContainerException;

public class Container implements IContainer {

	private JRoboClassLoader classLoader;
	private IStorage storage;
	private Resolver resolver;

	public Container() {
		storage = new Storage();
		resolver = new Resolver();
		classLoader = new JRoboClassLoader(storage, System.getProperty("java.class.path"));
		classLoader.loadClasses();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public <T> T get(Class<T> requiredInterface) throws JRoboContainerException {
		Class<?> resolvedClass = resolver.resolveClass(requiredInterface, storage.getImplementations(requiredInterface));
		if(storage.getInstance(resolvedClass) == null)
			storage.putInstance(resolvedClass, resolver.createNewInstance(resolvedClass));
		return (T)storage.getInstance(resolvedClass);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T create(Class<T> requiredInterface) throws JRoboContainerException {
		Class<?> resolvedClass = resolver.resolveClass(requiredInterface, storage.getImplementations(requiredInterface));
		return (T)resolver.createNewInstance(resolvedClass);
	}
}
