package overclocking.jrobocontainer.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 12:55
 * To change this template use File | Settings | File Templates.
 */
public class AmbiguousConstructorException extends JRoboContainerException {
    public AmbiguousConstructorException(Class<?> clazz) {
        super(clazz.getName() + " has multiple constructors annotated with @ContainerConstructor");
    }
}
