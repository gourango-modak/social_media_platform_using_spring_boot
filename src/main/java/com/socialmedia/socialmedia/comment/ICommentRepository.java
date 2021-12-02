package com.socialmedia.socialmedia.comment;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ICommentRepository extends JpaRepository<Comment, Long> {
    public List<Comment> findAllByStatusStatusId(Long statusId);
}
