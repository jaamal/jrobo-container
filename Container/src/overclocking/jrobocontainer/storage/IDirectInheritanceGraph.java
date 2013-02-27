package overclocking.jrobocontainer.storage;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 25.08.12
 * Time: 19:37
 * To change this template use File | Settings | File Templates.
 */
public interface IDirectInheritanceGraph {
    void addVertex(Class<?> vertex);
    void addEdge(Class<?> from, Class<?> to);
    ArrayList<Class<?>> getAdjacent(Class<?> vertex);
    ArrayList<Class<?>> getVertices();
}
