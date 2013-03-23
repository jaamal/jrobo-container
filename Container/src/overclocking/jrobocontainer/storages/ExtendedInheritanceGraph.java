package overclocking.jrobocontainer.storages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ExtendedInheritanceGraph implements IExtendedInheritanceGraph
{
    private IDirectInheritanceGraph directInheritanceGraph;

    private HashMap<ClassNode, ArrayList<ClassNode>> descendants;
    private HashMap<ClassNode, ArrayList<ClassNode>> ancestors;

    public ExtendedInheritanceGraph(IDirectInheritanceGraph directInheritanceGraph)
    {
        this.directInheritanceGraph = directInheritanceGraph;
        descendants = new HashMap<ClassNode, ArrayList<ClassNode>>();
        ancestors = new HashMap<ClassNode, ArrayList<ClassNode>>();
        HashSet<ClassNode> watchedClasses = new HashSet<ClassNode>();
        for (ClassNode clazz : directInheritanceGraph.getVertices())
        {
            ancestors.put(clazz, new ArrayList<ClassNode>());
            if (!watchedClasses.contains(clazz))
                dfs(clazz, watchedClasses);
        }
        for (ClassNode ancestor : directInheritanceGraph.getVertices())
            for (ClassNode descendant : descendants.get(ancestor))
            {
                ancestors.get(descendant).add(ancestor);
            }
    }


    private boolean isAbstractOrInterface(ClassNode clazz)
    {
        return clazz.isAbstract() || clazz.isInterface();
    }

    private void dfs(ClassNode clazz, HashSet<ClassNode> watchedClasses)
    {
        watchedClasses.add(clazz);
        for (ClassNode to : directInheritanceGraph.getAdjacent(clazz))
            if (!watchedClasses.contains(to))
                dfs(to, watchedClasses);
        HashSet<ClassNode> union = new HashSet<ClassNode>();
        for (ClassNode to : directInheritanceGraph.getAdjacent(clazz))
            union.addAll(descendants.get(to));
        if (!isAbstractOrInterface(clazz))
            union.add(clazz);
        descendants.put(clazz, new ArrayList<ClassNode>(union));
    }

    @Override
    public ArrayList<ClassNode> getDescendants(ClassNode abstraction)
    {
        if (descendants.containsKey(abstraction))
            return descendants.get(abstraction);
        return new ArrayList<ClassNode>();
    }

    @Override
    public ArrayList<ClassNode> getAncestors(ClassNode abstraction)
    {
        if (ancestors.containsKey(abstraction))
            return ancestors.get(abstraction);
        return new ArrayList<ClassNode>();
    }
}
