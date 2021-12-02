package com.socialmedia.socialmedia.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    public User findByUsername(String userName);
    @Query(
            value = "select user_id from user_and_user_role_map where user_id = ?1 and userrole_id = ?2",
            nativeQuery = true
    )
    public Long isRoleAssigned(Long userId, Long roleId);
    @Query(
            value = "select max(userrole_id) from user_and_user_role_map where  = ?1",
            nativeQuery = true
    )
    public Long getUserHighestRoleId(Long userId);
    @Query(
            value = "select user_id from user_and_user_role_map where user_id <> ?1 " +
                    "group by user_id having max(userrole_id) <= (select max(userrole_id) " +
                    "from user_and_user_role_map where user_id = ?1)",
            nativeQuery = true
    )
    public List<Long> getUsersByTheirRole(Long userId);
}
