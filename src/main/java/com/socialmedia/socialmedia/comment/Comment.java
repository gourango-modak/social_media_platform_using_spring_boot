package com.socialmedia.socialmedia.comment;

import com.socialmedia.socialmedia.status.Status;

import javax.persistence.*;

@Entity
@Table(schema = "Status_comments")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String description;
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Status status;

    public Comment(String description, Status status) {
        this.description = description;
        this.status = status;
    }

    public Comment() {

    }

    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
