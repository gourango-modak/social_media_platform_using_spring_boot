package com.socialmedia.socialmedia.login_user;

import com.socialmedia.socialmedia.message.APIMessage;
import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
public class LoginUserController {
    private final LoginUserService loginUserService;
    private final IUserService userService;

    public LoginUserController(LoginUserService loginUserService, IUserService userService) {
        this.loginUserService = loginUserService;
        this.userService = userService;
    }

    @GetMapping("/active_users")
    public ResponseEntity<Object> getAllActiveUsers() {
        List<User> userList = loginUserService.getAllActiveUsers();
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), userList));
    }

    @GetMapping("/logout_user")
    public ResponseEntity<Object> deleteLoginInfoOfAUser(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        loginUserService.deleteLoginInfoOfAUser(token);
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), "Log Out"));
    }

    @GetMapping("/add_acitve_user")
    public ResponseEntity<Object> saveLoginActiveUser(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        LoginUser loginUser= loginUserService.saveLoginUser(token);
        return ResponseEntity.ok(new APIMessage(HttpStatus.OK, new HashMap<>(), new HashMap<>(), loginUser));
    }
}
