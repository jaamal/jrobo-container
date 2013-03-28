package overclocking.jrobocontainer.storages;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 25.08.12
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public interface IDirectInheritanceGraph {
    void addVertex(String vertex);
    void addEdge(String from, String to);
    ArrayList<String> getAdjacent(String vertex);
    ArrayList<String> getVertices();
}
