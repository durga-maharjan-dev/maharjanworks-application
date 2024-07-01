package maharjanworks.exception;

public class EmployeeListIsEmptyException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	public EmployeeListIsEmptyException(String message) {
		super(message);
	}
	public EmployeeListIsEmptyException(String message, Throwable cause) {
		super(message,cause);
	}

}
