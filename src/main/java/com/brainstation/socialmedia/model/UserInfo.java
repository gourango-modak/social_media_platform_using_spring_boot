package com.brainstation.socialmedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userid;
	private String fullname;
	private String email;
	private String password;
	
	public UserInfo() {
		super();
	}
	public UserInfo(int usrId, String fullname, String email, String password) {
		super();
		this.userid = usrId;
		this.fullname = fullname;
		this.email = email;
		this.password = password;
	}

	
	public int getUserid() {
		return userid;
	}
	
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "User [fullname=" + fullname + ", email=" + email + ", password=" + password + "]";
	}
	
}
