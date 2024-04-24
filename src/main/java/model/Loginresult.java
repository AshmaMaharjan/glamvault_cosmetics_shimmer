package model;

public class Loginresult {

	
	private int status;
	private String role;
	public Loginresult(int status, String role) {
		super();
		this.status = status;
		this.role = role;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
}
