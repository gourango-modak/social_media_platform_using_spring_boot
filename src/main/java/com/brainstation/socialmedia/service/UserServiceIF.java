package com.brainstation.socialmedia.service;

import com.brainstation.socialmedia.model.UserInfo;

public interface UserServiceIF {
	public UserInfo saveUser(UserInfo usr);
	public UserInfo getUserByEmail(String email);
	public UserInfo getUserById(Integer Id);
}
