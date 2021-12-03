package com.socialmedia.socialmedia.comment;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.status.Status;
import com.socialmedia.socialmedia.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CommentService {
    @Autowired
    private final ICommentRepository commentRepository;
    private final StatusService statusService;

    public CommentService(ICommentRepository commentRepository, StatusService statusService) {
        this.commentRepository = commentRepository;
        this.statusService = statusService;
    }

    public List<Comment> findAllCommentByStatusId(Long statusId) {
        return commentRepository.findAllByStatusStatusId(statusId);
    }
    public Comment addCommentToAStatus(Long statusId, Comment comment) {
        Status status = null;
        try {
            status = statusService.findStatusByStatusId(statusId);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        comment.setStatus(status);
        return commentRepository.save(comment);
    }
}
