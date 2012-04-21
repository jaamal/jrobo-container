package exceptions;


@SuppressWarnings("serial")
public class AmbiguousImplementationMatchException extends
		JRoboContainerException {
	public AmbiguousImplementationMatchException(Class<?> requiredInterface)
	{
		super("More than one implementation of interface " + requiredInterface.getCanonicalName() + " found.");
	}

}
