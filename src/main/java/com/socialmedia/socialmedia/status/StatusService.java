package com.socialmedia.socialmedia.status;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.group.Group;
import com.socialmedia.socialmedia.group.GroupService;
import com.socialmedia.socialmedia.location.Location;
import com.socialmedia.socialmedia.location.LocationService;
import com.socialmedia.socialmedia.message.APIMessage;
import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Map.entry;

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

    public Status addStatus(String token, Long groupId, @RequestBody Map<String,Object> body) {
        User user = userService.getUserFromToken(token);
        return addStatusProperties(user, groupId, body);
    }

    public Status findStatusByStatusId(Long statusId) {
        return statusRepository.findById(statusId).get();
    }

    private Status addStatusProperties(User usr, Long groupId, @RequestBody Map<String,Object> body) {
        User user = usr;
        Group group = null;
        Location location = locationService.findByLocationName(body.get("location").toString().toLowerCase());
        Status status = new Status(body.get("statusDescription").toString(), body.get("privacy").toString(), null);
        if(groupId != null) {
            group = groupService.findGroupById(groupId);
            status.setGroup(group);
        }
        status.setUser(user);
        status.setLocation(location);
        statusRepository.save(status);
        return status;
    }
    public List<Status> getAllStatusOfAUser(String token) {
        User user = null;
        if(token != null) {
            user = userService.getUserFromToken(token);
            return statusRepository.findAllByUserUserId(user.getUserId());
        }
        return statusRepository.findAllByPrivacy("public");
    }

    public Status getStatusByUserIdAndStatusId(String token, Long statusId) {
        User user = userService.getUserFromToken(token);
        Status status = null;
        try {
            status = statusRepository.findByStatusIdAndUserUserId(statusId, user.getUserId());
        } catch (Exception e) {
            throw new ApiRequestException(new APIMessage(HttpStatus.BAD_REQUEST, Map.ofEntries(
                    entry("status", e.getMessage())
            ), new HashMap<>(), null));
        }
        return status;
    }

    public Status updateStatusByStatusId(String token, Long statusId, Map<String,Object> body) {
        User user = userService.getUserFromToken(token);
        Status status = new Status();
        status.setStatusId(statusId);
        Location location = locationService.findByLocationName(body.get("location").toString());
        status.setLocation(location);
        status.setStatusDescription(body.get("statusDescription").toString());
        status.setPrivacy(body.get("privacy").toString());
        status.setUser(user);
        try {
            status = statusRepository.save(status);
        } catch (Exception e) {
            throw new ApiRequestException(new APIMessage(HttpStatus.BAD_REQUEST, Map.ofEntries(
                    entry("status", e.getMessage())
            ), new HashMap<>(), null));
        }
        return status;
    }
}
