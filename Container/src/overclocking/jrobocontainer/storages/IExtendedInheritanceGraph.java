package overclocking.jrobocontainer.storages;

import java.util.ArrayList;

public interface IExtendedInheritanceGraph {
    ArrayList<ClassNode> getDescendants(ClassNode abstraction);
    ArrayList<ClassNode> getAncestors(ClassNode abstraction);
}
