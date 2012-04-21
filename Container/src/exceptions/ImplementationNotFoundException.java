package exceptions;

@SuppressWarnings("serial")
public class ImplementationNotFoundException extends JRoboContainerException {
	public ImplementationNotFoundException(Class<?> requiredInterface)
	{
		super("Can't find any implementation of interface" + requiredInterface.getCanonicalName() + ".");
	}
}
