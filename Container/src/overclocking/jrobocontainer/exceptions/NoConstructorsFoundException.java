package overclocking.jrobocontainer.exceptions;

public class NoConstructorsFoundException extends JroboContainerException {
    public NoConstructorsFoundException(Class<?> clazz)
    {
        super("No constructors found for " + clazz.getName());
    }
}
