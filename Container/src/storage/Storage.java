package storage;

import exceptions.InstanceForAbstractionNotFoundException;

import java.util.ArrayList;

public class Storage implements IStorage {

    IDirectInheritanceGraph directInheritanceGraph;
    IExtendedInheritanceGraph extendedInheritanceGraph;
    IInstancesManager instancesManager;

    public Storage() {
        directInheritanceGraph = new DirectInheritanceGraph();
        instancesManager = new InstancesManager();
    }

    @Override
    public <T> void addClass(Class<T> clazz) {
        Class<?>[] interfaces = clazz.getInterfaces();
        Class<?> superClass = clazz.getSuperclass();
        directInheritanceGraph.addVertex(clazz);
        for (Class<?> currentInterface : interfaces) {
            directInheritanceGraph.addEdge(currentInterface, clazz);
        }
        if (superClass != null && !superClass.equals(Object.class)) {
            directInheritanceGraph.addEdge(superClass, clazz);
        }
        instancesManager.putInstance(clazz, null);
    }

    @Override
    public ArrayList<Class<?>> getImplementations(Class<?> requiredAbstraction) {
        return extendedInheritanceGraph.getDescendants(requiredAbstraction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getInstance(Class<T> resolvedClass) throws InstanceForAbstractionNotFoundException {
        return instancesManager.getInstance(resolvedClass);
    }

    @Override
    public <T> void putInstance(Class<T> resolvedClass, Object newInstance) {
        instancesManager.putInstance(resolvedClass, newInstance);
    }

    @Override
    public void buildFullDiagram() {
        extendedInheritanceGraph = new ExtendedInheritanceGraph(directInheritanceGraph);
    }

    @Override
    public <T1, T2 extends T1> void bindInstance(Class<T1> abstraction, T2 instance) {
        putInstance(abstraction, instance);
        for(Class<?> ancestor : extendedInheritanceGraph.getAncestors(abstraction))
        {
            putInstance(ancestor, instance);
        }
    }

    @Override
    public boolean hasInstance(Class<?> requiredAbstraction) {
        return instancesManager.hasInstance(requiredAbstraction);
    }

}
