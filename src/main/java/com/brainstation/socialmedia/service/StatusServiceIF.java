package com.brainstation.socialmedia.service;

import java.util.List;

import com.brainstation.socialmedia.model.Status;

public interface StatusServiceIF {
	public Status saveStatus(Status st);
	public Status getStatusById(Integer status_id);
	public List<Status> getAllPostByUserID(int id);
	public List<Status> getAllPostByPrivacy(int privacy);
	public List<Status> getAllPostByPrivacyAndUserID(int privacy, int id);
}
