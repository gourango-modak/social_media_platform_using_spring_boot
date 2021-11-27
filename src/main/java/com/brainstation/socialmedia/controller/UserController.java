package com.brainstation.socialmedia.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.brainstation.socialmedia.model.Location;
import com.brainstation.socialmedia.model.Status;
import com.brainstation.socialmedia.model.UserInfo;
import com.brainstation.socialmedia.service.implementation.LocationService;
import com.brainstation.socialmedia.service.implementation.StatusService;
import com.brainstation.socialmedia.service.implementation.UserService;

@RestController
public class UserController {
	@Autowired
	private UserService userSev;
	@Autowired
	private StatusService stSev;
	@Autowired
	private LocationService locationService;
	
	public UserController(UserService userSev, StatusService stSev, LocationService locationService) {
		super();
		this.userSev = userSev;
		this.stSev = stSev;
		this.locationService = locationService;
	}

	@RequestMapping(method = RequestMethod.POST, value = "/register")
	public UserInfo saveUser(@RequestBody UserInfo usr) {
		UserInfo creUsr = userSev.getUserByEmail(usr.getEmail());
		if(creUsr != null)
			return null;
		return userSev.saveUser(usr);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/auth")
	public UserInfo getCredentialFlag(@RequestBody Map<String, String> data) {
		UserInfo cre_usr = userSev.getUserByEmail(data.get("email"));
		if(cre_usr == null) return null;
		if(data.get("password").equals(cre_usr.getPassword()))
			return cre_usr;
		return null;
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add_post/{id}")
	public Status savePost(@RequestBody Status st, @PathVariable int id) {
		UserInfo usr = userSev.getUserById(id);
		st.setUser(usr);
		return stSev.saveStatus(st);
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/add_post/{id}/{status_id}")
	public Status updatePost(@RequestBody Status st, @PathVariable int id, @PathVariable int status_id) {
		st.setId(status_id);
		UserInfo usr = userSev.getUserById(id);
		st.setUser(usr);
		return stSev.saveStatus(st);
	}
	
	
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{id}/{status_id}")
	public Status getPostByUserIDAndStatusID(@PathVariable int status_id) {
		Status status = stSev.getStatusById(status_id);
		return status;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/posts/{id}")
	public List<Status> getAllPostByUserID(@PathVariable int id) {
		List<Status> status = stSev.getAllPostByPrivacy(1);
		status.addAll(stSev.getAllPostByPrivacyAndUserID(0, id));
		return status;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/posts")
	public List<Status> getAllPost() {
		List<Status> status = stSev.getAllPostByPrivacy(1);
		return status;
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/locations")
	public List<Location> getAllLocations() {
		List<Location> locations = locationService.getAllLocations();
		return locations;
	}
}
