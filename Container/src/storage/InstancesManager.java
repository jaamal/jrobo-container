package storage;

import exceptions.InstanceForAbstractionNotFoundException;

import java.util.HashMap;

public class InstancesManager implements IInstancesManager
{
    private HashMap<Class<?>, Object> instances;

    public InstancesManager()
    {
        instances = new HashMap<Class<?>, Object>();
    }

    @Override
    public <T> void putInstance(Class<T> clazz, Object instance) {
        instances.put(clazz, instance);
    }

    @Override
    public <T> T getInstance(Class<T> clazz) throws InstanceForAbstractionNotFoundException {
        if(!instances.containsKey(clazz))
            throw new InstanceForAbstractionNotFoundException(clazz.getName());
        return (T) instances.get(clazz);
    }

    @Override
    public boolean hasInstance(Class<?> requiredAbstraction) {
        return instances.containsKey(requiredAbstraction) && instances.get(requiredAbstraction) != null;
    }
}
