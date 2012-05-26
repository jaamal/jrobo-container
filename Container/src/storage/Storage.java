package storage;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Storage implements IStorage {

    ArrayList<Class<?>> classes;
    HashMap<Class<?>, Object> instances;
    HashMap<Class<?>, ArrayList<Class<?>>> children;
    HashMap<Class<?>, ArrayList<Class<?>>> graph;

    public Storage() {
        classes = new ArrayList<Class<?>>();
        instances = new HashMap<Class<?>, Object>();
        children = new HashMap<Class<?>, ArrayList<Class<?>>>();
        graph = new HashMap<Class<?>, ArrayList<Class<?>>>();
    }

    private void addEdge(Class<?> parent, Class<?> child) {

        if (!graph.containsKey(parent))
            graph.put(parent,
                    new ArrayList<Class<?>>());
        graph.get(parent).add(child);
    }

    @Override
    public <T> void addClass(Class<T> clazz) {
        classes.add(clazz);
        Class<?>[] interfaces = clazz.getInterfaces();
        Class<?> superClass = clazz.getSuperclass();
        if (!graph.containsKey(clazz))
            graph.put(clazz, new ArrayList<Class<?>>());
        for (Class<?> currentInterface : interfaces) {
            addEdge(currentInterface, clazz);
        }
        if(superClass != null && !superClass.equals(Object.class))
            addEdge(superClass, clazz);
        getInstances().put(clazz, null);

    }

    @Override
    public ArrayList<Class<?>> getImplementations(Class<?> requiredAbstraction) {
        return children.get(requiredAbstraction);
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getInstance(Class<T> resolvedClass) {
        return (T) getInstances().get(resolvedClass);
    }

    @Override
    public <T> void putInstance(Class<T> resolvedClass, Object newInstance) {
        getInstances().put(resolvedClass, newInstance);

    }

    private boolean isAbstractOrInterface(Class<?> clazz)
    {
        return Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers());
    }

    private void dfs(Class<?> clazz, HashSet<Class<?>> watchedClasses)
    {
        watchedClasses.add(clazz);
        for(Class<?> to : graph.get(clazz))
            if(!watchedClasses.contains(to))
                dfs(to, watchedClasses);
        HashSet<Class<?>> union = new HashSet<Class<?>>();
        for(Class<?> to : graph.get(clazz))
            union.addAll(children.get(to));
        if(!isAbstractOrInterface(clazz))
            union.add(clazz);
        children.put(clazz, new ArrayList<Class<?>>(union));
    }

    @Override
    public void buildFullDiagram() {
        HashSet<Class<?>> watchedClasses = new HashSet<Class<?>>();
        for(Class<?> clazz : classes)
            if(!watchedClasses.contains(clazz))
                dfs(clazz, watchedClasses);
    }

    public void setInstances(HashMap<Class<?>, Object> instances) {
        this.instances = instances;
    }

    public HashMap<Class<?>, Object> getInstances() {
        return instances;
    }

}
