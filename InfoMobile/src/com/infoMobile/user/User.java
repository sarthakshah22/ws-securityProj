package com.infoMobile.user;
import java.io.Serializable;  

import javax.xml.bind.annotation.XmlElement; 
import javax.xml.bind.annotation.XmlRootElement; 
@XmlRootElement(name = "user") 
 
public class User implements Serializable {  
	private static final long serialVersionUID = 1L; 
	private String userID; 
	private String password; 
	private String email;  
	public User(){}
	public String getUserID() {
		return userID;
	}
	
	@XmlElement 
	public void setUserID(String userID) {
		this.userID = userID;
	}
	public String getPassword() {
		return password;
	}
	
	@XmlElement 
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	
	@XmlElement 
	public void setEmail(String email) {
		this.email = email;
	}
	public User(String userID, String password, String email) {
		super();
		this.userID = userID;
		this.password = password;
		this.email = email;
	}
	public User(String userID) {
		super();
		this.userID = userID;
	} 

} 
