package exceptions;

@SuppressWarnings("serial")
public class JRoboContainerException extends Exception{
	public JRoboContainerException(String message) {
		super(message);
	}
    public JRoboContainerException(String message, Exception parentException)
    {
        super(message + ": " + parentException.getMessage());
    }
	public JRoboContainerException() {
		super();
	}
}
