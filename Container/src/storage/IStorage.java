package storage;

import exceptions.InstanceForAbstractionNotFoundException;

import java.util.ArrayList;

public interface IStorage {
    <T> void addClass(Class<T> clazz);

    ArrayList<Class<?>> getImplementations(Class<?> requiredAbstraction);

    <T> Object getInstance(Class<T> resolvedClass) throws InstanceForAbstractionNotFoundException;

    <T> void putInstance(Class<T> resolvedClass, Object newInstance);

    void buildFullDiagram();

    <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance);

    boolean hasInstance(Class<?> requiredAbstraction);
}
