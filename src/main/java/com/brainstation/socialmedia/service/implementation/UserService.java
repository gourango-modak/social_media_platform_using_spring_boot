package com.brainstation.socialmedia.service.implementation;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainstation.socialmedia.model.UserInfo;
import com.brainstation.socialmedia.repository.UserRepository;
import com.brainstation.socialmedia.service.UserServiceIF;

@Service
public class UserService implements UserServiceIF {
	@Autowired
	private UserRepository userRepo;
	
	public UserService(UserRepository userRepo) {
		super();
		this.userRepo = userRepo;
	}
	
	@Override
	public UserInfo saveUser(UserInfo usr) {
		return userRepo.save(usr);
	}

	@Override
	public UserInfo getUserByEmail(String email) {
		return userRepo.findByEmail(email);
	}

	@Override
	public UserInfo getUserById(Integer Id) {
		return userRepo.findById(Id).get();
	}
	
	

}
