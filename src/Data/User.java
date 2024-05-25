package Data;

import com.fasterxml.jackson.annotation.JsonProperty;

public class User {
	@JsonProperty("password")
	private String password;
	@JsonProperty("name")
	private String name;
	@JsonProperty("userID")
	private int userID;
	
	public User(){
		
	}
	
	public User(String name, String password) { 
		this.name = name;
		this.password = password;
	 }

	public String getPassword() {
	 	 return password; 
	}

	public void setPassword(String password) { 
		 this.password = password; 
	}

	public String getName() {
	 	 return name; 
	}

	public void setName(String name) { 
		 this.name = name; 
	}

	public int getUserID() {
	 	 return userID; 
	}

	public void setUserID(int userID) { 
		 this.userID = userID; 
	}

	public void login() { 
		// TODO Auto-generated method
	 }

	public void logout() { 
		// TODO Auto-generated method
	 } 

}
