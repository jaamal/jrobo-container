package container;

import java.util.ArrayList;

import exceptions.AmbiguousImplementationMatchException;
import exceptions.ImplementationNotFoundException;
import exceptions.JRoboContainerException;

public class Resolver {
	
	public Class<?> resolveClass(Class<?> requiredInterface, ArrayList<Class<?>> implementations) throws JRoboContainerException
	{
		if (implementations.size() == 0) {
			throw new ImplementationNotFoundException(requiredInterface);
		}
		if (implementations.size() > 1) {
			throw new AmbiguousImplementationMatchException(requiredInterface);
		}
		return implementations.get(0);
	}

	public <T> T createNewInstance(Class<T> resolvedClass) {
		T result = null;
		try {
			result = resolvedClass.cast(resolvedClass.newInstance());
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
}
