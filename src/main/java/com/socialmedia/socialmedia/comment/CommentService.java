package com.socialmedia.socialmedia.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private final ICommentRepository commentRepository;

    public CommentService(ICommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }
    public List<Comment> findAllCommentByStatusId(Long statusId) {
        return commentRepository.findAllByStatusStatusId(statusId);
    }
    public Comment addCommentToAStatus(Comment comment) {
        return commentRepository.save(comment);
    }
}
