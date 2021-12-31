package com.socialmedia.socialmedia.login_user;

import com.socialmedia.socialmedia.status.Status;
import com.socialmedia.socialmedia.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface LoginUserRepository extends JpaRepository<LoginUser, Long> {
    @Query("FROM User AS u INNER JOIN LoginUser AS l ON u.userId = l.user.userId")
    List<User> findAllActiveUsers();
    void deleteByUserUserId(Long userId);
}
