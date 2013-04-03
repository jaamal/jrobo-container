package overclocking.jrobocontainer.exceptions;

import overclocking.jrobocontainer.storages.ClassNode;

public class CyclicalDependencyException extends JroboContainerException {
    public CyclicalDependencyException(ClassNode clazz)
    {
        super("Cyclical dependency found at " + clazz.getClassName());
    }
}
