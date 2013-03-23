package overclocking.jrobocontainer.storages;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectInheritanceGraph implements IDirectInheritanceGraph {

    HashMap<ClassNode, ArrayList<ClassNode>> graph;
    ArrayList<ClassNode> vertices;

    public DirectInheritanceGraph()
    {
        graph = new HashMap<ClassNode, ArrayList<ClassNode>>();
        vertices = new ArrayList<ClassNode>();
    }

    @Override
    public void addVertex(ClassNode vertex) {
        if(graph.containsKey(vertex))
            return;
        vertices.add(vertex);
        graph.put(vertex, new ArrayList<ClassNode>());
    }

    @Override
    public void addEdge(ClassNode from, ClassNode to) {
        if(!graph.containsKey(from))
            addVertex(from);
        graph.get(from).add(to);
    }

    @Override
    public ArrayList<ClassNode> getAdjacent(ClassNode vertex) {
        return graph.get(vertex);
    }

    @Override
    public ArrayList<ClassNode> getVertices() {
        return vertices;
    }
}
