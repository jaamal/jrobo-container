package container;

import storage.IStorage;

import java.util.ArrayList;

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
	public ArrayList<Class<?>> getImplementations(Class<?> requiredAbstraction) {
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

    @Override
    public void buildFullDiagram() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public boolean hasInstance(Class<?> requiredAbstraction) {
        return false;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
