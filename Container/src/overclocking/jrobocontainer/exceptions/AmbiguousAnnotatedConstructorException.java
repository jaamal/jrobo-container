package overclocking.jrobocontainer.exceptions;

public class AmbiguousAnnotatedConstructorException extends JroboContainerException {
    public AmbiguousAnnotatedConstructorException(Class<?> clazz) {
        super(clazz.getName() + " has multiple constructors annotated with @ContainerConstructor");
    }
}
