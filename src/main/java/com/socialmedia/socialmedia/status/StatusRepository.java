package com.socialmedia.socialmedia.status;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StatusRepository extends JpaRepository<Status, Long> {
    @Query("FROM Status AS s WHERE s.user.userId = ?1")
    List<Status> findAllByUserUserId(Long userId);
    @Query("FROM Status AS s WHERE s.privacy = ?1")
    List<Status> findAllByPrivacy(String privacy);
    Status findByStatusIdAndUserUserId(Long statusId, Long userId);
}
