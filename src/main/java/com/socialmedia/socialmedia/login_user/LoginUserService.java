package com.socialmedia.socialmedia.login_user;

import com.socialmedia.socialmedia.user.IUserService;
import com.socialmedia.socialmedia.user.User;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class LoginUserService {
    private IUserService userService;
    private LoginUserRepository loginUserRepository;

    public LoginUserService(IUserService userService, LoginUserRepository loginUserRepository) {
        this.userService = userService;
        this.loginUserRepository = loginUserRepository;
    }

    public List<User> getAllActiveUsers() {
        return loginUserRepository.findAllActiveUsers();
    }
    @Transactional
    public void deleteLoginInfoOfAUser(String token) {
        User user = userService.getUserFromToken(token);
        loginUserRepository.deleteByUserUserId(user.getUserId());
    }

    public LoginUser saveLoginUser(String token) {
        User user = userService.getUserFromToken(token);
        LoginUser loginUser = new LoginUser();
        loginUser.setDate(LocalDateTime.now());
        loginUser.setUser(user);
        return loginUserRepository.save(loginUser);
    }
}
