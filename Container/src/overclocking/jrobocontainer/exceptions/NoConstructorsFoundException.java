package overclocking.jrobocontainer.exceptions;

/**
 * Created with IntelliJ IDEA.
 * User: mv146
 * Date: 17.09.12
 * Time: 12:49
 * To change this template use File | Settings | File Templates.
 */
public class NoConstructorsFoundException extends JroboContainerException {
    public NoConstructorsFoundException(Class<?> clazz)
    {
        super("No constructors found for " + clazz.getName());
    }
}
