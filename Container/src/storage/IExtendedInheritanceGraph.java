package storage;

import java.util.ArrayList;

public interface IExtendedInheritanceGraph {
    ArrayList<Class<?>> getChildren(Class<?> abstraction);
}
