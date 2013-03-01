package overclocking.jrobocontainer.storage;

import overclocking.jrobocontainer.configurations.AutoConfiguration;
import overclocking.jrobocontainer.configurations.ConfigurationsManager;
import overclocking.jrobocontainer.configurations.IConfiguration;
import overclocking.jrobocontainer.configurations.IConfigurationsManager;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage implements IStorage
{
    IDirectInheritanceGraph directInheritanceGraph;
    IExtendedInheritanceGraph extendedInheritanceGraph;
    HashMap<Class<?>, Object> synchronizeObjects;
    IConfigurationsManager configurationsManager;

    public Storage()
    {
        synchronizeObjects = new HashMap<Class<?>, Object>();
        directInheritanceGraph = new DirectInheritanceGraph();
        configurationsManager = new ConfigurationsManager();
    }

    @Override
    public <T> void addClass(Class<T> clazz)
    {
        configurationsManager.setConfiguration(clazz, new AutoConfiguration(this, clazz));
        synchronizeObjects.put(clazz, new Object());
        Class<?>[] interfaces = clazz.getInterfaces();
        Class<?> superClass = clazz.getSuperclass();
        directInheritanceGraph.addVertex(clazz);
        for (Class<?> currentInterface : interfaces)
        {
            directInheritanceGraph.addEdge(currentInterface, clazz);
        }
        if (superClass != null && !superClass.equals(Object.class))
        {
            directInheritanceGraph.addEdge(superClass, clazz);
        }
    }

    @Override
    public ArrayList<Class<?>> getImplementations(Class<?> requiredAbstraction)
    {
        return extendedInheritanceGraph.getDescendants(requiredAbstraction);
    }

    @Override
    public void buildExtendedInheritanceGraph()
    {
        extendedInheritanceGraph = new ExtendedInheritanceGraph(directInheritanceGraph);
    }

    @Override
    public Object getSynchronizeObject(Class<?> resolvedClass)
    {
        if(synchronizeObjects.containsKey(resolvedClass))
            return synchronizeObjects.get(resolvedClass);
        throw new JRoboContainerException("No synchronize object found for " + resolvedClass.getCanonicalName());
    }

    @Override
    public <T> IConfiguration getConfiguration(Class<T> abstraction)
    {
        return configurationsManager.getConfiguration(abstraction);
    }

    @Override
    public <T> void setConfiguration(Class<T> abstraction, IConfiguration configuration)
    {
        configurationsManager.setConfiguration(abstraction, configuration);
    }
}
