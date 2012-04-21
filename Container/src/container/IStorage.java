package container;

import java.util.ArrayList;

public interface IStorage {
	<T> void addClass(Class<T> clazz);
	
	ArrayList<Class<?>> getImplementations(Class<?> requiredInterface);

	<T> Object getInstance(Class<T> resolvedClass);

	<T> void putInstance(Class<T> resolvedClass, Object newInstance);
}
