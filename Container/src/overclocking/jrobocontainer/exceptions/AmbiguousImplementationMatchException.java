package overclocking.jrobocontainer.exceptions;


import overclocking.jrobocontainer.storages.ClassNode;

public class AmbiguousImplementationMatchException extends
        JroboContainerException {
	public AmbiguousImplementationMatchException(ClassNode requiredInterface, String implementations)
	{
		super("More than one implementation of interface " + requiredInterface.getClassName() + " found:\n" + implementations);
	}

}
