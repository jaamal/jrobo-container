package classloader;

import exceptions.AmbiguousImplementationMatchException;
import exceptions.ImplementationNotFoundException;
import exceptions.JRoboContainerException;

import java.util.ArrayList;

public class Resolver {

	public Class<?> resolveClass(Class<?> requiredAbstraction,
			ArrayList<Class<?>> implementations) throws JRoboContainerException {
		if (implementations == null || implementations.size() == 0) {
			throw new ImplementationNotFoundException(requiredAbstraction);
		}
		if (implementations.size() > 1) {
			throw new AmbiguousImplementationMatchException(requiredAbstraction);
		}
		return implementations.get(0);
	}

	public <T> T createNewInstance(Class<T> resolvedClass) {
		T result = null;
		try {
			result = resolvedClass.cast(resolvedClass.newInstance());
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		return result;

	}
}

