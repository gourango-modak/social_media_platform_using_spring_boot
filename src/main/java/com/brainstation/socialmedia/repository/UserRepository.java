package com.brainstation.socialmedia.repository;

import org.springframework.data.repository.CrudRepository;

import com.brainstation.socialmedia.model.UserInfo;

public interface UserRepository extends CrudRepository<UserInfo, Integer> {
	public UserInfo findByEmail(String email);
//	public UserInfo findById(Integer id);
}
