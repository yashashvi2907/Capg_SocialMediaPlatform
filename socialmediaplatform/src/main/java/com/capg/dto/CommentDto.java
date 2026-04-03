package com.capg.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Comment entity.
 * Used to transfer comment-related data between layers.
 */
public class CommentDto {

    /**
     * Unique identifier of the comment.
     */
    private Integer commentID;

    /**
     * Content of the comment.
     */
    private String commentText;

    /**
     * Timestamp when the comment was created.
     */
    private LocalDateTime timestamp;

    /**
     * Unique identifier of the user who created the comment.
     */
    private Integer userID;

    /**
     * Username of the comment author.
     */
    private String username;

    /**
     * Unique identifier of the associated post.
     */
    private Integer postID;

    /**
     * Default constructor.
     */
    public CommentDto() {
        // default constructor
    }

    /**
     * Gets the comment ID.
     * @return comment ID
     */
    public Integer getCommentID() {
        return commentID;
    }

    /**
     * Sets the comment ID.
     * @param commentID unique identifier of the comment
     */
    public void setCommentID(final Integer commentID) {
        this.commentID = commentID;
    }

    /**
     * Gets the comment text.
     * @return comment content
     */
    public String getCommentText() {
        return commentText;
    }

    /**
     * Sets the comment text.
     * @param commentText content of the comment
     */
    public void setCommentText(final String commentText) {
        this.commentText = commentText;
    }

    /**
     * Gets the timestamp of the comment.
     * @return comment timestamp
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the timestamp of the comment.
     * @param timestamp time when the comment was created
     */
    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the user ID of the comment author.
     * @return user ID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets the user ID of the comment author.
     * @param userID unique identifier of the user
     */
    public void setUserID(final Integer userID) {
        this.userID = userID;
    }

    /**
     * Gets the username of the comment author.
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the comment author.
     * @param username name of the user
     */
    public void setUsername(final String username) {
        this.username = username;
    }

    /**
     * Gets the associated post ID.
     * @return post ID
     */
    public Integer getPostID() {
        return postID;
    }

    /**
     * Sets the associated post ID.
     * @param postID unique identifier of the post
     */
    public void setPostID(final Integer postID) {
        this.postID = postID;
    }
}