package com.socialmedia.socialmedia.group;

import com.socialmedia.socialmedia.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class GroupController {
    private final GroupService groupService;
    private final IUserService userService;

    @Autowired
    public GroupController(GroupService groupService, IUserService userService) {
        this.groupService = groupService;
        this.userService = userService;
    }

    @PostMapping("/add_group")
    public Group addGroup(@RequestBody Group group) {
        return groupService.saveGroup(group);
    }
//    @GetMapping("/{userId}/{groupId}")
//    public Group addUserToAGroup(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId) {
//        return groupService.addUserToAGroup(userId, groupId);
//    }
}
