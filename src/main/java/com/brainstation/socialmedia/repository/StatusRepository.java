package com.brainstation.socialmedia.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.brainstation.socialmedia.model.Status;

public interface StatusRepository extends CrudRepository<Status, Integer> {
	public List<Status> findByUserinfoUserid(Integer userinfo_id);
	public List<Status> findByPrivacy(Integer privacy);
	public List<Status> findByPrivacyAndUserinfoUserid(Integer privacy, Integer Id);
}
