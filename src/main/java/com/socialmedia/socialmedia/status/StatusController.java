package com.socialmedia.socialmedia.status;

import com.socialmedia.socialmedia.message.APIMessage;
import com.socialmedia.socialmedia.security.utils.JwtUtil;
import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class StatusController {
    @Autowired
    private final StatusService statusService;


    public StatusController(StatusService statusService) {
        this.statusService = statusService;
    }

    @PostMapping("/add_status")
    public ResponseEntity<Object> addStatus(@RequestHeader HttpHeaders headers, @RequestBody Map<String,Object> body) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        Status status = statusService.addStatus(token, null, body);
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), status));
    }

    @GetMapping("/posts")
    public ResponseEntity<Object> getAllStatusOfAUser(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        List<Status> statusList = null;
        if(token.equals("undefined")) {
            statusList = statusService.getAllStatusOfAUser(null);
        } else
            statusList = statusService.getAllStatusOfAUser(token);
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), statusList));
    }
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAllStatusOfAUser(@RequestHeader HttpHeaders headers, @PathVariable("id") Long stausId) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        Status status = statusService.getStatusByUserIdAndStatusId(token, stausId);
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), status));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateStatusOfAUser(@RequestHeader HttpHeaders headers, @PathVariable("id") Long stausId, @RequestBody Map<String,Object> body) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        Status update_status = statusService.updateStatusByStatusId(token, stausId, body);
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), update_status));
    }

//    @PostMapping("/{userId}/{groupId}/add_status")
//    public Status addStatus(@PathVariable("userId") Long userId, @PathVariable("groupId") Long groupId, @RequestBody Map<String,Object> body) {
//        return statusService.addStatus(userId, groupId, body);
//    }
}
