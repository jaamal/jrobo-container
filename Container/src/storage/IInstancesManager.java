package storage;

import exceptions.InstanceForAbstractionNotFoundException;

public interface IInstancesManager {
    <T> void putInstance(Class<T> clazz, Object instance);
    <T> T getInstance(Class<T> clazz) throws InstanceForAbstractionNotFoundException;

    boolean hasInstance(Class<?> requiredAbstraction);
}

