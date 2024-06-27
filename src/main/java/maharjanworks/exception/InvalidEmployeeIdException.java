package maharjanworks.exception;

public class InvalidEmployeeIdException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public InvalidEmployeeIdException(String message) {
		super(message);
	}

	public InvalidEmployeeIdException(String message, Throwable cause) {
		super(message, cause);
	}

}
