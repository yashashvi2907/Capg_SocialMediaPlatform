package com.capg.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Entity representing a comment made by a user on a post.
 * Maps to the "comments" table in the database.
 */
@Entity
@Table(name = "comments")
public class Comment {

    /**
     * Unique identifier of the comment.
     */
    @Id
    @Column(name = "commentID")
    private Integer commentID;

    /**
     * Content of the comment.
     */
    @Column(name = "comment_text")
    private String commentText;

    /**
     * Timestamp when the comment was created.
     */
    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    /**
     * User who created the comment.
     */
    @ManyToOne
    @JoinColumn(name = "userID")
    private User user;

    /**
     * Post associated with this comment.
     * Uses JsonBackReference to prevent infinite recursion during serialization.
     */
    @ManyToOne
    @JoinColumn(name = "postID")
    @JsonBackReference
    private Post post;

    /**
     * Default constructor.
     */
    public Comment() {
        // default constructor
    }

    public Integer getCommentID() {
        return commentID;
    }

    public void setCommentID(final Integer commentID) {
        this.commentID = commentID;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(final String commentText) {
        this.commentText = commentText;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(final User user) {
        this.user = user;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(final Post post) {
        this.post = post;
    }
}