package model;
import java.io.Serializable;

public class Login implements Serializable {
	private String userId;
	private String pass;

	public Login() {}
	public Login(String userId, String pass) {
		this.userId = userId;
		this.pass = pass;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
}
