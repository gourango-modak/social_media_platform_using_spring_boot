package com.brainstation.socialmedia.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Autowired;

@Entity
@Table(name="Status")
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int Id;
	private String statusDes;
	private String location;
	public int getId() {
		return Id;
	}
	public void setId(int id) {
		Id = id;
	}
	private int privacy;
	@ManyToOne
	@Autowired
	private UserInfo userinfo;
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Status() {
		super();
	}
	public String getStatusDes() {
		return statusDes;
	}

	public Status(int id, String statusDes, String location, int privacy, UserInfo userinfo) {
		super();
		Id = id;
		this.statusDes = statusDes;
		this.location = location;
		this.privacy = privacy;
		this.userinfo = userinfo;
	}
	public void setStatusDes(String statusDes) {
		this.statusDes = statusDes;
	}
	public int getPrivacy() {
		return privacy;
	}
	public void setPrivacy(int privacy) {
		this.privacy = privacy;
	}
	public UserInfo getUser() {
		return userinfo;
	}
	public void setUser(UserInfo user) {
		this.userinfo = user;
	}
	@Override
	public String toString() {
		return "Status [ID=" + Id + ", statusDes=" + statusDes + ", privacy=" + privacy + ", usr=" + userinfo
				+ "]";
	}
}
