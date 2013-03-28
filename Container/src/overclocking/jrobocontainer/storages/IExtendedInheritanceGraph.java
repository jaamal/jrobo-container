package overclocking.jrobocontainer.storages;

import java.util.ArrayList;

public interface IExtendedInheritanceGraph {
    ArrayList<String> getDescendants(String abstractionId);
    ArrayList<String> getAncestors(String abstractionId);
}
