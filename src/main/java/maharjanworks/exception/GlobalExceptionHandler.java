package maharjanworks.exception;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(value = {EmailAlreadyExistsException.class})
	public ResponseEntity<?> handleEmailAlreadyExistsException(EmailAlreadyExistsException ex, WebRequest webRequest){
		
		HttpStatus httpStatus = HttpStatus.FOUND;
		
		ApplicationStatus applicationStatus = new ApplicationStatus(
				ex.getMessage(),
				ex.getCause(), 
				httpStatus,
				ZonedDateTime.now(ZoneId.of("Z")),
				webRequest.getDescription(true));
		return new ResponseEntity<>(applicationStatus, httpStatus);
	}
	
	@ExceptionHandler(value = {InvalidEmployeeIdException.class})
	public ResponseEntity<?> handleInvalidEmployeeIdException(InvalidEmployeeIdException ex, WebRequest webRequest){
		
		HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
		
		ApplicationStatus applicationStatus = new ApplicationStatus(
				ex.getMessage(),
				ex.getCause(),
				httpStatus,
				ZonedDateTime.now(ZoneId.of("Z")),
				webRequest.getDescription(false));
		return new ResponseEntity<>(applicationStatus, httpStatus);
	}
	
	@ExceptionHandler(value = {EmployeeNotFoundException.class})
	public ResponseEntity<?> handleEmployeeNotFoundException(EmployeeNotFoundException ex, WebRequest webRequest){
		
		HttpStatus httpStatus = HttpStatus.NOT_FOUND;
		ApplicationStatus applicationStatus = new ApplicationStatus(
				ex.getMessage(),
				ex.getCause(),
				httpStatus,
				ZonedDateTime.now(ZoneId.of("Z")),
				webRequest.getDescription(false)
				);
		return new ResponseEntity<>(applicationStatus,httpStatus);
	}

}
