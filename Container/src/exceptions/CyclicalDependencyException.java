package exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 13:59
 * To change this template use File | Settings | File Templates.
 */
public class CyclicalDependencyException extends JRoboContainerException {
    public CyclicalDependencyException(Class<?> clazz)
    {
        super("Cyclical dependency found at " + clazz.getName());
    }
}
