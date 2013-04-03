package overclocking.jrobocontainer.exceptions;

public class AmbiguousConstructorException extends JroboContainerException
{
    public <T> AmbiguousConstructorException(Class<T> clazz, String constructors)
    {
        super(clazz + " has many constructors and no one marked as @ContainerConstructor: " + constructors);
    }
}
