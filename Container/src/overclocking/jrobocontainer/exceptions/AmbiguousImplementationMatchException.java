package overclocking.jrobocontainer.exceptions;


import overclocking.jrobocontainer.storages.ClassNode;

@SuppressWarnings("serial")
public class AmbiguousImplementationMatchException extends
		JRoboContainerException {
	public AmbiguousImplementationMatchException(ClassNode requiredInterface, String implementations)
	{
		super("More than one implementation of interface " + requiredInterface.getClassName() + " found:\n" + implementations);
	}

}
