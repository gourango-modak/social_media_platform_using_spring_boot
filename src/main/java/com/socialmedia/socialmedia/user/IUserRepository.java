package com.socialmedia.socialmedia.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUsername(String userName);
    List<User> findAllByUserRoleRoleIdLessThanEqual(Long roleId);
    User findByEmail(String email);
}
