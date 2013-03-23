package overclocking.jrobocontainer.storages;

import org.apache.bcel.classfile.JavaClass;
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
    HashMap<ClassNode, Object> synchronizeObjects;
    IConfigurationsManager configurationsManager;
    ILoadingContext loadingContext;
    IClassNodesStorage classNodesStorage;

    public Storage(IClassNodesStorage classNodesStorage)
    {
        this.classNodesStorage = classNodesStorage;
        synchronizeObjects = new HashMap<ClassNode, Object>();
        directInheritanceGraph = new DirectInheritanceGraph();
        configurationsManager = new ConfigurationsManager();
    }

    @Override
    public void addClass(JavaClass clazz, ILoadingContext loadingContext)
    {
        if (this.loadingContext == null)
            this.loadingContext = loadingContext;
        ClassNode node = classNodesStorage.getClassNode(clazz.getClassName());
        configurationsManager.setConfiguration(node, new AutoConfiguration(this, node));
        synchronizeObjects.put(node, new Object());
        String[] interfacesNames = clazz.getInterfaceNames();
        String superClassName = clazz.getSuperclassName();
        directInheritanceGraph.addVertex(node);
        for (String currentInterface : interfacesNames)
        {
            directInheritanceGraph.addEdge(classNodesStorage.getClassNode(currentInterface), node);
        }
        directInheritanceGraph.addEdge(classNodesStorage.getClassNode(superClassName), node);
    }

    @Override
    public ArrayList<ClassNode> getImplementations(ClassNode requiredAbstraction)
    {
        return extendedInheritanceGraph.getDescendants(requiredAbstraction);
    }

    @Override
    public void buildExtendedInheritanceGraph()
    {
        extendedInheritanceGraph = new ExtendedInheritanceGraph(directInheritanceGraph);
    }

    @Override
    public Object getSynchronizeObject(ClassNode resolvedClass)
    {
        if (synchronizeObjects.containsKey(resolvedClass))
            return synchronizeObjects.get(resolvedClass);
        throw new JRoboContainerException("No synchronize object found for " + resolvedClass.getClassName());
    }

    @Override
    public IConfiguration getConfiguration(ClassNode abstraction)
    {
        return configurationsManager.getConfiguration(abstraction);
    }

    @Override
    public void setConfiguration(ClassNode abstraction, IConfiguration configuration)
    {
        configurationsManager.setConfiguration(abstraction, configuration);
    }
}
