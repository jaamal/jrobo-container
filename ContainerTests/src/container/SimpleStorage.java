package container;

import java.util.ArrayList;

import storage.IStorage;

public class SimpleStorage implements IStorage {
	private ArrayList<Class<?>> list;

	public SimpleStorage() {
		list = new ArrayList<Class<?>>();
	}

	@Override
	public <T> void addClass(Class<T> clazz) {
		list.add(clazz);
	}

	public ArrayList<Class<?>> getLoadedClasses() {
		return list;
	}

	public void clear() {
		list.clear();
	}


	@Override
	public ArrayList<Class<?>> getImplementations(Class<?> requiredInterface) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Object getInstance(Class<T> resolvedClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void putInstance(Class<T> resolvedClass, Object newInstance) {
		// TODO Auto-generated method stub
		
	}

}
