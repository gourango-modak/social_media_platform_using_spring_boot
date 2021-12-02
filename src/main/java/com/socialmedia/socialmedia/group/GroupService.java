package com.socialmedia.socialmedia.group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class GroupService {
    @Autowired
    private final IGroupRepository groupRepository;

    public GroupService(IGroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Group findById(Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group addUserToGroup(Group group) {
        return groupRepository.save(group);
    }
}
