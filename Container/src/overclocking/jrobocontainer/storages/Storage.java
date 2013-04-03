package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
import overclocking.jrobocontainer.configurations.AutoConfiguration;
import overclocking.jrobocontainer.configurations.ConfigurationsManager;
import overclocking.jrobocontainer.configurations.IConfiguration;
import overclocking.jrobocontainer.configurations.IConfigurationsManager;
import overclocking.jrobocontainer.exceptions.JroboContainerException;
import overclocking.jrobocontainer.logging.ILoadingLog;

import java.util.ArrayList;
import java.util.HashMap;

public class Storage implements IStorage
{
    private IDirectInheritanceGraph directInheritanceGraph;
    private IExtendedInheritanceGraph extendedInheritanceGraph;
    private HashMap<String, Object> synchronizeObjects;
    private IConfigurationsManager configurationsManager;
    private ILoadingLog log;
    private IClassNodesStorage classNodesStorage;

    public Storage(IClassNodesStorage classNodesStorage)
    {
        this.classNodesStorage = classNodesStorage;
        synchronizeObjects = new HashMap<String, Object>();
        directInheritanceGraph = new DirectInheritanceGraph();
        configurationsManager = new ConfigurationsManager(classNodesStorage);
    }

    @Override
    public void addClass(JavaClass clazz, ILoadingLog log)
    {
        if (this.log == null)
            this.log = log;
        String nodeId = classNodesStorage.getClassId(clazz);
        configurationsManager.setConfiguration(nodeId, new AutoConfiguration(this, classNodesStorage, nodeId));
        synchronizeObjects.put(nodeId, new Object());
        String[] interfacesNames = clazz.getInterfaceNames();
        String superClassName = clazz.getSuperclassName();
        directInheritanceGraph.addVertex(nodeId);
        for (String currentInterface : interfacesNames)
        {
            directInheritanceGraph.addEdge(classNodesStorage.getClassId(currentInterface), nodeId);
        }
        directInheritanceGraph.addEdge(classNodesStorage.getClassId(superClassName), nodeId);
    }

    @Override
    public ArrayList<String> getImplementations(String requiredAbstractionId)
    {
        return extendedInheritanceGraph.getDescendants(requiredAbstractionId);
    }

    @Override
    public void buildExtendedInheritanceGraph()
    {
        extendedInheritanceGraph = new ExtendedInheritanceGraph(directInheritanceGraph, classNodesStorage);
    }

    @Override
    public Object getSynchronizeObject(String resolvedClassId)
    {
        if (synchronizeObjects.containsKey(resolvedClassId))
            return synchronizeObjects.get(resolvedClassId);
        throw new JroboContainerException("No synchronize object found for " + resolvedClassId);
    }

    @Override
    public IConfiguration getConfiguration(String abstractionId)
    {
        return configurationsManager.getConfiguration(abstractionId);
    }

    @Override
    public void setConfiguration(String abstractionId, IConfiguration configuration)
    {
        configurationsManager.setConfiguration(abstractionId, configuration);
    }
}
