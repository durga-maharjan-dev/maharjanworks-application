package maharjanworks.exception;

public class EmailAlreadyExistsException extends RuntimeException{


	public EmailAlreadyExistsException(String email) {
		super(email);
	}
}