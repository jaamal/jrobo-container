package testclasses.fakestorages;

import overclocking.jrobocontainer.configurations.IConfiguration;
import overclocking.jrobocontainer.loadingcontext.ILoadingContext;
import overclocking.jrobocontainer.storage.IStorage;

import java.util.ArrayList;

public class SimpleStorage implements IStorage {
	private ArrayList<Class<?>> list;

	public SimpleStorage() {
		list = new ArrayList<Class<?>>();
	}

	@Override
	public <T> void addClass(Class<T> clazz, ILoadingContext loadingContext) {
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
    public void buildExtendedInheritanceGraph() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public Object getSynchronizeObject(Class<?> resolvedClass) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> IConfiguration getConfiguration(Class<T> abstraction) {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public <T> void setConfiguration(Class<T> abstraction, IConfiguration configuration)
    {
    }

    @Override
    public ArrayList<Class<?>> getAllClasses()
    {
        return list;
    }

}
