package model;

public class LoginModel {
	private String username;
	private String password;

	public LoginModel(String user_name, String password ){
		super();
		this.username = user_name;
		this.password = password;
		
	}
	


	



	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}



	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
}
