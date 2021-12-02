package com.socialmedia.socialmedia.status;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.group.Group;
import com.socialmedia.socialmedia.group.GroupService;
import com.socialmedia.socialmedia.location.Location;
import com.socialmedia.socialmedia.location.LocationService;
import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;
import java.util.Map;

@RestController
public class StatusController {
    @Autowired
    private final StatusService statusService;
    private final IUserService userService;
    private final LocationService locationService;
    private final GroupService groupService;

    public StatusController(StatusService statusService, IUserService userService, LocationService locationService, GroupService groupService) {
        this.statusService = statusService;
        this.userService = userService;
        this.locationService = locationService;
        this.groupService = groupService;
    }

    @PostMapping("/{userId}/add_status")
    public Status addStatus(@PathVariable("userId") Long userId, @RequestBody Map<String,Object> body) {
        Status status = addStatusProperties(userId, null, body);
        return statusService.addStatus(status);
    }

    private Status addStatusProperties(Long userId, Long groupId, @RequestBody Map<String,Object> body) {
        User user = null;
        Group group = null;
        Location location = locationService.findByLocationName(body.get("location").toString().toLowerCase());
        if(location == null) throw new ApiRequestException("Location is not available", HttpStatus.NOT_FOUND);
        Status status = new Status(body.get("status_des").toString(), Integer.parseInt(body.get("privacy").toString()), null);
        try {
            user = userService.getUserByUserId(userId);
            if(groupId != null) group = groupService.findById(groupId);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        if(groupId != null) status.setGroup(group);
        status.setUser(user);
        status.setLocation(location);
        return status;
    }

    @PostMapping("/{userId}/{groupId}/add_status")
    public Status addStatus(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId, @RequestBody Map<String,Object> body) {
        Status status = addStatusProperties(userId, groupId, body);
        return statusService.addStatus(status);
    }
}
