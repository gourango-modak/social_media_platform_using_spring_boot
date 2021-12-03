package com.socialmedia.socialmedia.status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class StatusController {
    @Autowired
    private final StatusService statusService;

    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/{userId}/add_status")
    public Status addStatus(@PathVariable("userId") Long userId, @RequestBody Map<String,Object> body) {
        return statusService.addStatus(userId, null, body);
    }

    @PostMapping("/{userId}/{groupId}/add_status")
    public Status addStatus(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId, @RequestBody Map<String,Object> body) {
        return statusService.addStatus(userId, groupId, body);
    }
}
