package overclocking.jrobocontainer.storages;

import java.util.ArrayList;

public interface IDirectInheritanceGraph {
    void addVertex(String vertex);
    void addEdge(String from, String to);
    ArrayList<String> getAdjacent(String vertex);
    ArrayList<String> getVertices();
}
