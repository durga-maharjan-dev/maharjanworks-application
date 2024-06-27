package maharjanworks.exception;

import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

public class ApplicationStatus {
	
	private final String message;
	private final Throwable cause;
	private final HttpStatus httpStatus;
	private final ZonedDateTime timestamp;
	private final String webRquest;
	
	public ApplicationStatus(String message, Throwable cause, HttpStatus httpStatus, ZonedDateTime timestamp, String webRequest) {
		super();
		this.message = message;
		this.cause = cause;
		this.httpStatus = httpStatus;
		this.timestamp = timestamp;
		this.webRquest = webRequest;
	}

	public String getMessage() {
		return message;
	}

	public Throwable getCause() {
		return cause;
	}

	public HttpStatus getHttpStatus() {
		return httpStatus;
	}

	public ZonedDateTime getTimestamp() {
		return timestamp;
	}

	public String getWebRquest() {
		return webRquest;
	}
	
	
	
	
	
	
	
	
	
	
}
