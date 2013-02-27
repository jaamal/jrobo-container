package overclocking.jrobocontainer.exceptions;

@SuppressWarnings("serial")
public class ImplementationNotFoundException extends JRoboContainerException {
	public ImplementationNotFoundException(Class<?> requiredAbstraction)
	{
		super("Can't find any implementation for abstraction " + requiredAbstraction.getCanonicalName() + ".");
	}
}
