package container;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage implements IStorage {

	ArrayList<Class<?>> classes;
	HashMap<Class<?>, Object> instances;
	HashMap<Class<?>, ArrayList<Class<?>>> interfacesImplementations;
	
	public Storage()
	{
		classes = new ArrayList<Class<?>>();
		instances = new HashMap<Class<?>, Object>();
		interfacesImplementations = new HashMap<Class<?>, ArrayList<Class<?>>>();
	}

	@Override
	public <T> void addClass(Class<T> clazz) {
		classes.add(clazz);
		Class<?>[] interfaces = clazz.getInterfaces();
		if (clazz.isInterface()) {
			if (!interfacesImplementations.containsKey(clazz))
				interfacesImplementations.put(clazz, new ArrayList<Class<?>>());
		} else {
			for (Class<?> currentInterface : interfaces) {
				if (!interfacesImplementations.containsKey(currentInterface))
					interfacesImplementations.put(currentInterface,
							new ArrayList<Class<?>>());
				interfacesImplementations.get(currentInterface).add(clazz);
			}
			getInstances().put(clazz, null);
		}
	}
	
	@Override
	public ArrayList<Class<?>> getImplementations(Class<?> requiredInterface)
	{
		return interfacesImplementations.get(requiredInterface);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T> T getInstance(Class<T> resolvedClass) {
		return (T)getInstances().get(resolvedClass);
	}

	@Override
	public <T> void putInstance(Class<T> resolvedClass, Object newInstance) {
		getInstances().put(resolvedClass, newInstance);
		
	}

	public void setInstances(HashMap<Class<?>, Object> instances) {
		this.instances = instances;
	}

	public HashMap<Class<?>, Object> getInstances() {
		return instances;
	}

}
