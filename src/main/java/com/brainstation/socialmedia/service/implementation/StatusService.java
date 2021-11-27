package com.brainstation.socialmedia.service.implementation;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.brainstation.socialmedia.model.Status;
import com.brainstation.socialmedia.repository.StatusRepository;
import com.brainstation.socialmedia.service.StatusServiceIF;

@Service
public class StatusService implements StatusServiceIF {
	@Autowired
	private StatusRepository stRep;

	public StatusService(StatusRepository stRep) {
		super();
		this.stRep = stRep;
	}

	@Override
	public Status saveStatus(Status st) {
		return stRep.save(st);
	}

	@Override
	public List<Status> getAllPostByUserID(int id) {
		return stRep.findByUserinfoUserid(id);
	}

	@Override
	public List<Status> getAllPostByPrivacy(int privacy) {
		return stRep.findByPrivacy(privacy);
	}

	@Override
	public Status getStatusById(Integer status_id) {
		return stRep.findById(status_id).get();
	}

	@Override
	public List<Status> getAllPostByPrivacyAndUserID(int privacy, int id) {
		return stRep.findByPrivacyAndUserinfoUserid(privacy, id);
	}
}
