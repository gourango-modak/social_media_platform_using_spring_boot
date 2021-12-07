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
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
public class StatusService {
    @Autowired
    private final StatusRepository statusRepository;
    private final LocationService locationService;
    private final GroupService groupService;
    private final IUserService userService;


    public StatusService(StatusRepository statusRepository, LocationService locationService, GroupService groupService, IUserService userService) {
        this.statusRepository = statusRepository;
        this.locationService = locationService;
        this.groupService = groupService;
        this.userService = userService;
    }

    public Status addStatus(Long userId, Long groupId, @RequestBody Map<String,Object> body) {
        return addStatusProperties(userId, groupId, body);
    }

    public Status findStatusByStatusId(Long statusId) {
        return statusRepository.findById(statusId).get();
    }

    private Status addStatusProperties(Long userId, Long groupId, @RequestBody Map<String,Object> body) {
        User user = null;
        Group group = null;
        Location location = null;
        Status status = new Status(body.get("status_des").toString(), Integer.parseInt(body.get("privacy").toString()), null);
        try {
            user = userService.getUserByUserId(userId);
            if(groupId != null) group = groupService.findGroupById(groupId);
            location = locationService.findByLocationName(body.get("location").toString().toLowerCase());
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        if(groupId != null) status.setGroup(group);
        status.setUser(user);
        status.setLocation(location);
        return status;
    }
}
