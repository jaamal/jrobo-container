package overclocking.jrobocontainer.exceptions;


@SuppressWarnings("serial")
public class AmbiguousImplementationMatchException extends
		JRoboContainerException {
	public AmbiguousImplementationMatchException(Class<?> requiredInterface, String implementations)
	{
		super("More than one implementation of interface " + requiredInterface.getCanonicalName() + " found:\n" + implementations);
	}

}
