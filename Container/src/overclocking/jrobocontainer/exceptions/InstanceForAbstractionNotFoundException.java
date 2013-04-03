package overclocking.jrobocontainer.exceptions;

public class InstanceForAbstractionNotFoundException extends JroboContainerException {
    public InstanceForAbstractionNotFoundException(String message) {
        super("Instance not found for abstraction " + message + ".");
    }
}
