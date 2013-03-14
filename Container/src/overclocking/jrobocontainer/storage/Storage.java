package overclocking.jrobocontainer.storage;

import overclocking.jrobocontainer.configurations.AutoConfiguration;
import overclocking.jrobocontainer.configurations.ConfigurationsManager;
import overclocking.jrobocontainer.configurations.IConfiguration;
import overclocking.jrobocontainer.configurations.IConfigurationsManager;
import overclocking.jrobocontainer.exceptions.JRoboContainerException;
import overclocking.jrobocontainer.loadingcontext.ILoadingContext;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage implements IStorage
{
    IDirectInheritanceGraph directInheritanceGraph;
    IExtendedInheritanceGraph extendedInheritanceGraph;
    HashMap<Class<?>, Object> synchronizeObjects;
    IConfigurationsManager configurationsManager;
    ArrayList<Class<?>> allClasses;
    ILoadingContext loadingContext;

    public Storage()
    {
        synchronizeObjects = new HashMap<Class<?>, Object>();
        directInheritanceGraph = new DirectInheritanceGraph();
        configurationsManager = new ConfigurationsManager();
        allClasses = new ArrayList<Class<?>>();
    }

    @Override
    public <T> void addClass(Class<T> clazz, ILoadingContext loadingContext)
    {
        if(this.loadingContext == null)
            this.loadingContext = loadingContext;
        allClasses.add(clazz);
        configurationsManager.setConfiguration(clazz, new AutoConfiguration(this, clazz));
        //loadingContext.appendToLog(configurationsManager.getConfiguration(clazz).toString() + " for " + clazz);
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
        loadingContext.appendToLog("Asking for " + abstraction.toString() + ", loader is " + abstraction.getClassLoader());
        return configurationsManager.getConfiguration(abstraction);
    }

    @Override
    public <T> void setConfiguration(Class<T> abstraction, IConfiguration configuration)
    {
        configurationsManager.setConfiguration(abstraction, configuration);
    }

    @Override
    public ArrayList<Class<?>> getAllClasses()
    {
        return allClasses;
    }
}
