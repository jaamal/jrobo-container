package overclocking.jrobocontainer.exceptions;

import overclocking.jrobocontainer.injectioncontext.IInjectionContext;

public class JroboContainerException extends RuntimeException{

    private static final String delimiter = "------------------------------------------\r\n";
	public JroboContainerException(String message) {
		super(message);
	}

    public JroboContainerException(String message, IInjectionContext injectionContext, Exception parentException)
    {
        super(injectionContext.getLog() + delimiter + message + ": ", parentException);
    }

    public JroboContainerException(String message, Exception parentException)
    {
        super(message, parentException);
    }
	public JroboContainerException() {
		super();
	}
}
