package overclocking.jrobocontainer.storages;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectInheritanceGraph implements IDirectInheritanceGraph {

    private HashMap<String, ArrayList<String>> graph;
    private ArrayList<String> vertices;

    public DirectInheritanceGraph()
    {
        graph = new HashMap<String, ArrayList<String>>();
        vertices = new ArrayList<String>();
    }

    @Override
    public void addVertex(String vertex) {
        if(graph.containsKey(vertex))
            return;
        vertices.add(vertex);
        graph.put(vertex, new ArrayList<String>());
    }

    @Override
    public void addEdge(String from, String to) {
        if(!graph.containsKey(from))
            addVertex(from);
        graph.get(from).add(to);
    }

    @Override
    public ArrayList<String> getAdjacent(String vertex) {
        return graph.get(vertex);
    }

    @Override
    public ArrayList<String> getVertices() {
        return vertices;
    }
}
