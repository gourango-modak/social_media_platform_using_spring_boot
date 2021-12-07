package com.socialmedia.socialmedia.user;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.message.APIMessage;
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

import java.util.*;

import static java.util.Map.entry;

@Service
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
    public List<User> getAllUsers(String userName) {
        User user = userRepository.findByUsername(userName);
        List<User> userList = userRepository.findAllByUserRoleRoleIdLessThanEqual(user.getUserRole().getRoleId());
        return userList;
    }


    @Override
    public User saveUser(User user) {
        User getUserByName = userRepository.findByUsername(user.getUsername());
        User getUserByEmail = userRepository.findByEmail(user.getEmail());
        if(getUserByName != null && getUserByEmail != null) throw new ApiRequestException(new APIMessage(HttpStatus.FOUND, Map.ofEntries(
                entry("username", "username is already available."),
                entry("email", "email is already available.")
        ), new HashMap<>(), null));
        else if(getUserByName != null && getUserByEmail == null) throw new ApiRequestException(new APIMessage(HttpStatus.FOUND, Map.ofEntries(
                entry("username", "username is already available.")
        ), new HashMap<>(), null));
        else if(getUserByName == null && getUserByEmail != null) throw new ApiRequestException(new APIMessage(HttpStatus.FOUND, Map.ofEntries(
                entry("email", "email is already available.")
        ), new HashMap<>(), null));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public UserRole saveRole(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }


    @Override
    public void updateUserRole(Long userId, UserRole userRole) {
        UserRole userRoleDB = null;
        try {
            userRoleDB = userRoleRepository.findByName(userRole.getName());
        } catch (Exception e) {
            System.out.println(e.getMessage());
//            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        User user = userRepository.findById(userId).get();
        user.setUserRole(userRoleDB);
        saveUser(user);
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if(user == null)
            System.out.println("Error");
//            throw new ApiRequestException("User Not Found!!", HttpStatus.FORBIDDEN);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
