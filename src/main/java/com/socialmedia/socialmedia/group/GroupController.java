package com.socialmedia.socialmedia.group;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class GroupController {
    @Autowired
    private final GroupService groupService;
    private final IUserService userService;

    public GroupController(GroupService groupService, IUserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @PostMapping("/add_group")
    public Group addGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
    @GetMapping("/{userId}/{groupId}")
    public Group addUserToGroup(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId) {
        User user = null;
        Group group = null;
        try {
            user = userService.getUserByUserId(userId);
            group = groupService.findById(groupId);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        List<User> listUsers = group.getUsers();
        listUsers.add(user);
        group.setUser(listUsers);
        return groupService.addUserToGroup(group);
    }
}
