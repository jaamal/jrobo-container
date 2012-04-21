package exceptions;

@SuppressWarnings("serial")
public class JRoboContainerException extends Exception{
	public JRoboContainerException(String message) {
		super(message);
	}
	public JRoboContainerException() {
		super();
	}
}
