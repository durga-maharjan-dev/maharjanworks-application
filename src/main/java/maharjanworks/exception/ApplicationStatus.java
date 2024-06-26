package maharjanworks.exception;

public class ApplicationStatus {
	
	private String message;
	private String webRequest;
	
	public ApplicationStatus(String message, String webRequest) {
		super();
		this.message = message;
		this.webRequest = webRequest;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getWebRequest() {
		return webRequest;
	}

	public void setWebRequest(String webRequest) {
		this.webRequest = webRequest;
	}

}
