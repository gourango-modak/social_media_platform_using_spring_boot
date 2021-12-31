package com.socialmedia.socialmedia.user;

import com.socialmedia.socialmedia.user.role.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    User getUserByUserName(String userName);
    void updateUserRole(Long userId, UserRole userRole);
    User saveUser(User user);
    UserRole saveRole(UserRole userRole);
    User getUserByUserId(Long userId);
    User getUserByEmail(String email);
    UserDetails getUserByEmailAndPassword(String email, String password);
    List<User> getAllUsers(String userName);
    UserDetails loadUserByUsername(String username);
    User getUserFromToken(String token);
}
