package storage;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ExtendedInheritanceGraph implements IExtendedInheritanceGraph {
    private IDirectInheritanceGraph directInheritanceGraph;

    private HashMap<Class<?>, ArrayList<Class<?>>> children;

    public ExtendedInheritanceGraph(IDirectInheritanceGraph directInheritanceGraph) {
        this.directInheritanceGraph = directInheritanceGraph;
        children = new HashMap<Class<?>, ArrayList<Class<?>>>();
        HashSet<Class<?>> watchedClasses = new HashSet<Class<?>>();
        for (Class<?> clazz : directInheritanceGraph.getVertices())
            if (!watchedClasses.contains(clazz))
                dfs(clazz, watchedClasses);
    }


    private boolean isAbstractOrInterface(Class<?> clazz) {
        return Modifier.isAbstract(clazz.getModifiers()) || Modifier.isInterface(clazz.getModifiers());
    }

    private void dfs(Class<?> clazz, HashSet<Class<?>> watchedClasses) {
        watchedClasses.add(clazz);
        for (Class<?> to : directInheritanceGraph.getAdjacent(clazz))
            if (!watchedClasses.contains(to))
                dfs(to, watchedClasses);
        HashSet<Class<?>> union = new HashSet<Class<?>>();
        for (Class<?> to : directInheritanceGraph.getAdjacent(clazz))
            union.addAll(children.get(to));
        if (!isAbstractOrInterface(clazz))
            union.add(clazz);
        children.put(clazz, new ArrayList<Class<?>>(union));
    }

    @Override
    public ArrayList<Class<?>> getChildren(Class<?> abstraction) {
        return children.get(abstraction);
    }
}
