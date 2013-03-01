package overclocking.jrobocontainer.exceptions;

import overclocking.jrobocontainer.injectioncontext.IInjectionContext;

@SuppressWarnings("serial")
public class JRoboContainerException extends RuntimeException{

    private static final String delimiter = "------------------------------------------\r\n";
	public JRoboContainerException(String message) {
		super(message);
	}

    public JRoboContainerException(String message, IInjectionContext injectionContext, Exception parentException)
    {
        super(injectionContext.getLog() + delimiter + message + ": " + parentException.getMessage());
    }

    public JRoboContainerException(String message, Exception parentException)
    {
        super(message + ": " + parentException.getMessage());
    }
	public JRoboContainerException() {
		super();
	}
}
