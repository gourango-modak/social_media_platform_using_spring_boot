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

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
        User db_user = userRepository.findByUsername(user.getUsername());
        if(db_user != null) throw new ApiRequestException("Username is available!!", HttpStatus.CONFLICT);
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
            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
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
            throw new ApiRequestException("User Not Found!!", HttpStatus.FORBIDDEN);
        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), authorities);
    }
}
