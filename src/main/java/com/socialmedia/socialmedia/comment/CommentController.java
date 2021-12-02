package com.socialmedia.socialmedia.comment;

import com.socialmedia.socialmedia.exception.ApiRequestException;
import com.socialmedia.socialmedia.status.Status;
import com.socialmedia.socialmedia.status.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {
    @Autowired
    private final CommentService commentService;
    private final StatusService statusService;

    public CommentController(CommentService commentService, StatusService statusService) {
        this.commentService = commentService;
        this.statusService = statusService;
    }

    @GetMapping("/{statusId}/comments")
    public List<Comment> getAllCommentByStatusId(@PathVariable("statusId") Long statusId) {
        return commentService.findAllCommentByStatusId(statusId);
    }
    @PostMapping("/{statusId}/add_comment")
    public Comment addCommentToAStatus(@PathVariable("statusId") Long statusId, @RequestBody Comment comment) {
        Status status = null;
        try {
            status = statusService.findByStatusId(statusId);
        } catch (Exception e) {
            throw new ApiRequestException(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        comment.setStatus(status);
        return commentService.addCommentToAStatus(comment);
    }
}
