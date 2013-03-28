package overclocking.jrobocontainer.storages;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class ExtendedInheritanceGraph implements IExtendedInheritanceGraph
{
    private IDirectInheritanceGraph directInheritanceGraph;
    private IClassNodesStorage classNodesStorage;

    private HashMap<String, ArrayList<String>> descendants;
    private HashMap<String, ArrayList<String>> ancestors;

    public ExtendedInheritanceGraph(IDirectInheritanceGraph directInheritanceGraph, IClassNodesStorage classNodesStorage)
    {
        this.directInheritanceGraph = directInheritanceGraph;
        this.classNodesStorage = classNodesStorage;
        descendants = new HashMap<String, ArrayList<String>>();
        ancestors = new HashMap<String, ArrayList<String>>();
        HashSet<String> watchedClasses = new HashSet<String>();
        for (String classId : directInheritanceGraph.getVertices())
        {
            ancestors.put(classId, new ArrayList<String>());
            if (!watchedClasses.contains(classId))
                dfs(classId, watchedClasses);
        }
        for (String ancestor : directInheritanceGraph.getVertices())
            for (String descendant : descendants.get(ancestor))
            {
                ancestors.get(descendant).add(ancestor);
            }
    }


    private boolean isAbstractOrInterface(String classId)
    {
        ClassNode node = classNodesStorage.getClassNodeById(classId);
        return node.isAbstract() || node.isInterface();
    }

    private void dfs(String classId, HashSet<String> watchedClasses)
    {
        watchedClasses.add(classId);
        for (String to : directInheritanceGraph.getAdjacent(classId))
            if (!watchedClasses.contains(to))
                dfs(to, watchedClasses);
        HashSet<String> union = new HashSet<String>();
        for (String to : directInheritanceGraph.getAdjacent(classId))
            union.addAll(descendants.get(to));
        if (!isAbstractOrInterface(classId))
            union.add(classId);
        descendants.put(classId, new ArrayList<String>(union));
    }

    @Override
    public ArrayList<String> getDescendants(String abstraction)
    {
        if (descendants.containsKey(abstraction))
            return descendants.get(abstraction);
        return new ArrayList<String>();
    }

    @Override
    public ArrayList<String> getAncestors(String abstraction)
    {
        if (ancestors.containsKey(abstraction))
            return ancestors.get(abstraction);
        return new ArrayList<String>();
    }
}
