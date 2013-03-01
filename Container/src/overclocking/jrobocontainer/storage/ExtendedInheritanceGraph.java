package overclocking.jrobocontainer.storage;

import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ExtendedInheritanceGraph implements IExtendedInheritanceGraph {
    private IDirectInheritanceGraph directInheritanceGraph;

    private HashMap<Class<?>, ArrayList<Class<?>>> descendants;
    private HashMap<Class<?>, ArrayList<Class<?>>> ancestors;

    public ExtendedInheritanceGraph(IDirectInheritanceGraph directInheritanceGraph) {
        this.directInheritanceGraph = directInheritanceGraph;
        descendants = new HashMap<Class<?>, ArrayList<Class<?>>>();
        ancestors = new HashMap<Class<?>, ArrayList<Class<?>>>();
        HashSet<Class<?>> watchedClasses = new HashSet<Class<?>>();
        for (Class<?> clazz : directInheritanceGraph.getVertices())
        {
            ancestors.put(clazz, new ArrayList<Class<?>>());
            if (!watchedClasses.contains(clazz))
                dfs(clazz, watchedClasses);
        }
        for (Class<?> ancestor : directInheritanceGraph.getVertices())
            for (Class<?> descendant : descendants.get(ancestor)) {
                ancestors.get(descendant).add(ancestor);
            }
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
            union.addAll(descendants.get(to));
        if (!isAbstractOrInterface(clazz))
            union.add(clazz);
        descendants.put(clazz, new ArrayList<Class<?>>(union));
    }

    @Override
    public ArrayList<Class<?>> getDescendants(Class<?> abstraction) {
        if(descendants.containsKey(abstraction))
            return descendants.get(abstraction);
        return new ArrayList<Class<?>>();
    }

    @Override
    public ArrayList<Class<?>> getAncestors(Class<?> abstraction) {
        if(ancestors.containsKey(abstraction))
            return ancestors.get(abstraction);
        return new ArrayList<Class<?>>();
    }
}
