package com.socialmedia.socialmedia.user;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.security.utils.JwtUtil;
import com.socialmedia.socialmedia.user.role.UserRole;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {
    private final IUserService userService;
    private final JwtUtil jwtUtil;
    @Autowired
    public UserController(IUserService userService, JwtUtil jwtUtil) {
        this.userService = userService;
        this.jwtUtil = jwtUtil;
    }

    @GetMapping("/users")
    public List<User> getAllUsers(@RequestHeader HttpHeaders headers) {
        String token = headers.get("Authorization").get(0).replace("Bearer ", "");
        String userName = jwtUtil.extractUsername(token);
        return userService.getAllUsers(userName);
    }

    @PostMapping("/add_role")
    public UserRole addRole(@RequestBody UserRole userRole) {
        return userService.saveRole(userRole);
    }

    @PostMapping("/add_user")
    public User addUser(@RequestBody User user) {
        return userService.saveUser(user);
    }
    @PostMapping("/{userId}/update_role")
    public void updateUserRole(@PathVariable("userId") Long userId, @RequestBody UserRole userRole) {
        userService.updateUserRole(userId, userRole);
    }

    @GetMapping("/refresh_token")
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader(AUTHORIZATION);
        if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");
            String userName = null;
            try {
                userName = jwtUtil.extractUsername(token);
            } catch (ExpiredJwtException e) {
                throw new ApiRequestException(e.getMessage(), FORBIDDEN);
            } catch (SignatureException e) {
                throw new ApiRequestException(e.getMessage(), FORBIDDEN);
            }
            UserDetails userDetails = userService.loadUserByUsername(userName);

            if (jwtUtil.validateToken(token, userDetails)) {
                String access_token = jwtUtil.generateToken(userDetails, 120);
                String refresh_token = jwtUtil.generateToken(userDetails, 180);
                Map<String, String> tokens = new HashMap<>();
                tokens.put("access-token", access_token);
                tokens.put("refresh-token", refresh_token);
                response.setContentType(APPLICATION_JSON_VALUE);
                new ObjectMapper().writeValue(response.getOutputStream(), tokens);
            }
        } else {
            throw new ApiRequestException("Token is not Passed", FORBIDDEN);
        }
    }

}
