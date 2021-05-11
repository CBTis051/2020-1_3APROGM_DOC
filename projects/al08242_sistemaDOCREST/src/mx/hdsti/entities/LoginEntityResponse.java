package mx.hdsti.entities;

public class LoginEntityResponse {
	
	private String ok;
	private String resultMessage;
	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}

	public String getOk() {
		return ok;
	}

	public void setOk(String resultCode) {
		this.ok = resultCode;
	}
	
	@Override
	public String toString() {
		return "LoginEntityResponse string representation.";
	}
	
}
