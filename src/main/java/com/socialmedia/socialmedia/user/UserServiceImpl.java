package com.socialmedia.socialmedia.user;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.user.role.UserRole;
import com.socialmedia.socialmedia.user.role.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
@Service
@Transactional
public class UserServiceImpl implements IUserService, UserDetailsService {
    private final IUserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(IUserRepository userRepository, UserRoleRepository userRoleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserRole saveRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public void addRoleToUser(String userName, String roleName) {
        User user = userRepository.findByUsername(userName);
        UserRole userRole = userRoleRepository.findByName(roleName);
        user.getUserRoles().add(userRole);
    }

    @Override
    public Long isRoleAssigned(Long userId, String roleName) {
        UserRole userRole = userRoleRepository.findByName(roleName);
        if(userRole == null) throw new ApiRequestException("Role is Not Found", HttpStatus.NOT_FOUND);
        return userRepository.isRoleAssigned(userId, userRole.getRoleId());
    }

    @Override
    public User getUserByUserName(String userName) {
        return userRepository.findByUsername(userName);
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId).get();
    }

    @Override
    public Long getUserHighestRoleId(String userName) {
        User user = userRepository.findByUsername(userName);
        return userRepository.getUserHighestRoleId(user.getUserId());
    }

    @Override
    public List<User> getAllUsers(Long userId) {
        List<Long> userIdList = userRepository.getUsersByTheirRole(userId);
        List<User> userList = new ArrayList<>();
        userIdList.forEach(Id-> userList.add(getUserByUserId(Id)));
        return userList;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            throw new ApiRequestException("User Not Found!!", HttpStatus.FORBIDDEN);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        user.getUserRoles().forEach(userRole -> authorities.add(new SimpleGrantedAuthority(userRole.getName())));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
