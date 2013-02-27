package overclocking.jrobocontainer.storage;

import overclocking.jrobocontainer.configurations.IConfiguration;

import java.util.ArrayList;

public interface IStorage {
    <T> void addClass(Class<T> clazz);

    ArrayList<Class<?>> getImplementations(Class<?> requiredAbstraction);

    void buildExtendedInheritanceGraph();

    Object getSynchronizeObject(Class<?> resolvedClass);

    <T> IConfiguration getConfiguration(Class<T> abstraction);

    <T> void setConfiguration(Class<T> abstraction, IConfiguration configuration);
}
