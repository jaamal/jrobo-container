package overclocking.jrobocontainer.exceptions;

import overclocking.jrobocontainer.storages.ClassNode;

@SuppressWarnings("serial")
public class ImplementationNotFoundException extends JRoboContainerException {
	public ImplementationNotFoundException(ClassNode requiredAbstraction)
	{
		super("Can't find any implementation for abstraction " + requiredAbstraction.getClassName() + ".");
	}
}
