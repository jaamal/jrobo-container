package overclocking.jrobocontainer.storage;

import java.util.ArrayList;
import java.util.HashMap;

public class DirectInheritanceGraph implements IDirectInheritanceGraph {

    HashMap<Class<?>, ArrayList<Class<?>>> graph;
    ArrayList<Class<?>> vertices;

    public DirectInheritanceGraph()
    {
        graph = new HashMap<Class<?>, ArrayList<Class<?>>>();
        vertices = new ArrayList<Class<?>>();
    }

    @Override
    public void addVertex(Class<?> vertex) {
        if(graph.containsKey(vertex))
            return;
        vertices.add(vertex);
        graph.put(vertex, new ArrayList<Class<?>>());
    }

    @Override
    public void addEdge(Class<?> from, Class<?> to) {
        if(!graph.containsKey(from))
            addVertex(from);
        graph.get(from).add(to);
    }

    @Override
    public ArrayList<Class<?>> getAdjacent(Class<?> vertex) {
        return graph.get(vertex);
    }

    @Override
    public ArrayList<Class<?>> getVertices() {
        return vertices;
    }
}
