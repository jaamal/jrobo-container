package exceptions;

@SuppressWarnings("serial")
public class JRoboContainerException extends RuntimeException{
	public JRoboContainerException(String message) {
		super(message);
	}
	public JRoboContainerException() {
		super();
	}
}
