package maharjanworks.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(EmailAlreadyExistsException.class)
	public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest){
		ApplicationStatus applicationStatus = new ApplicationStatus(ex.getMessage(),webRequest.getDescription(true));
		return new ResponseEntity<>(applicationStatus, HttpStatus.FOUND);
	}

}
