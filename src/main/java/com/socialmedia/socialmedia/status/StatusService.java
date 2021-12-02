package com.socialmedia.socialmedia.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;

    public StatusService(StatusRepository statusRepository) {
        this.statusRepository = statusRepository;
    }

    public Status addStatus(Status status) {
        return statusRepository.save(status);
    }
    public Status findByStatusId(Long statusId) {
        return statusRepository.findById(statusId).get();
    }
}
