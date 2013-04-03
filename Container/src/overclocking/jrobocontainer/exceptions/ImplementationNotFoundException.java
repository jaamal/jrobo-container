package overclocking.jrobocontainer.exceptions;

import overclocking.jrobocontainer.storages.ClassNode;

public class ImplementationNotFoundException extends JroboContainerException {
	public ImplementationNotFoundException(ClassNode requiredAbstraction)
	{
		super("Can't find any implementation for abstraction " + requiredAbstraction.getClassName() + ".");
	}
}
