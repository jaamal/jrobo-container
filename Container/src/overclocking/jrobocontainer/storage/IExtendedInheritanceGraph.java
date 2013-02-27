package overclocking.jrobocontainer.storage;

import java.util.ArrayList;

public interface IExtendedInheritanceGraph {
    ArrayList<Class<?>> getDescendants(Class<?> abstraction);
    ArrayList<Class<?>> getAncestors(Class<?> abstraction);
}
