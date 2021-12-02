package com.socialmedia.socialmedia.user;

import com.socialmedia.socialmedia.user.role.UserRole;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface IUserService {
    public User saveUser(User user);
    public UserRole saveRole(UserRole userRole);
    public void addRoleToUser(String username, String roleName);
    public Long isRoleAssigned(Long userId, String roleName);
    public User getUserByUserName(String userName);
    public User getUserByUserId(Long userId);
    public Long getUserHighestRoleId(String userName);
    public List<User> getAllUsers(Long userId);
    public UserDetails loadUserByUsername(String username);
}
