package com.socialmedia.socialmedia.group;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class GroupService {
    @Autowired
    private final IGroupRepository groupRepository;
    private final IUserService userService;

    public GroupService(IGroupRepository groupRepository, IUserService userService) {
        this.groupRepository = groupRepository;
        this.userService = userService;
    }

    public Group findGroupById(Long groupId) {
        return groupRepository.findById(groupId).get();
    }

    public Group saveGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group addUserToAGroup(Long userId, Long groupId) {
        User user = null;
        Group group = null;
        try {
            user = userService.getUserByUserId(userId);
            group = groupRepository.findById(groupId).get();
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        List<User> listUsers = group.getUsers();
        listUsers.add(user);
        group.setUser(listUsers);
        return groupRepository.save(group);
    }
}
