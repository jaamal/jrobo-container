package overclocking.jrobocontainer.exceptions;

import overclocking.jrobocontainer.storages.ClassNode;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class CyclicalDependencyException extends JroboContainerException {
    public CyclicalDependencyException(ClassNode clazz)
    {
        super("Cyclical dependency found at " + clazz.getClassName());
    }
}
