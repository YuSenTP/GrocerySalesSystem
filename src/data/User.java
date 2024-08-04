package data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({"userID", "name", "password", "role", "picFile"}) //To change the save order to json
public class User {
	@JsonProperty("password")
	private String password;
	@JsonProperty("name")
	private String name;
	@JsonProperty("userID")
	private int userID;
	@JsonProperty("role")
	private String role;
	@JsonProperty("picFile")
	private String picFile;
	
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
	
	public String getRole(){
		return role;
	}
	
	public void setRole(String role){
		this.role = role; 
	}
	
	public String getPicFile() {
		return picFile;
	}

	public void setPicFile(String picFile) {
		this.picFile = picFile;
	}

	public void login() { 
		// TODO Auto-generated method
	 }

	public void logout() { 
		// TODO Auto-generated method
	 } 

}
