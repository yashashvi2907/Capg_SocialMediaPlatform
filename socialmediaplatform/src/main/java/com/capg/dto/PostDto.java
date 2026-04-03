package com.capg.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object (DTO) for representing post details.
 * <p>
 * This class is used to transfer post-related data between layers,
 * including post content, metadata, user details, and engagement metrics.
 * </p>
 */
public class PostDto {

    private Integer postID;
    private String content;
    private LocalDateTime timestamp;

    private Integer userID;
    private String username;

    private int likeCount;
    private int commentCount;

    /**
     * Default constructor.
     */
    public PostDto() {
    }

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param postID        the unique identifier of the post
     * @param content       the textual content of the post
     * @param timestamp     the creation timestamp of the post
     * @param userID        the ID of the user who created the post
     * @param username      the username of the post creator
     * @param likeCount     the total number of likes on the post
     * @param commentCount  the total number of comments on the post
     */
    public PostDto(Integer postID, String content, LocalDateTime timestamp,
                   Integer userID, String username,
                   int likeCount, int commentCount) {
        this.postID = postID;
        this.content = content;
        this.timestamp = timestamp;
        this.userID = userID;
        this.username = username;
        this.likeCount = likeCount;
        this.commentCount = commentCount;
    }

    /**
     * Gets the post ID.
     *
     * @return the post ID
     */
    public Integer getPostID() {
        return postID;
    }

    /**
     * Sets the post ID.
     *
     * @param postID the post ID to set
     */
    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    /**
     * Gets the post content.
     *
     * @return the post content
     */
    public String getContent() {
        return content;
    }

    /**
     * Sets the post content.
     *
     * @param content the content to set
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Gets the post creation timestamp.
     *
     * @return the timestamp of the post
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the post creation timestamp.
     *
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * Gets the user ID of the post creator.
     *
     * @return the user ID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * Sets the user ID of the post creator.
     *
     * @param userID the user ID to set
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * Gets the username of the post creator.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username of the post creator.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets the number of likes on the post.
     *
     * @return the like count
     */
    public int getLikeCount() {
        return likeCount;
    }

    /**
     * Sets the number of likes on the post.
     *
     * @param likeCount the like count to set
     */
    public void setLikeCount(int likeCount) {
        this.likeCount = likeCount;
    }

    /**
     * Gets the number of comments on the post.
     *
     * @return the comment count
     */
    public int getCommentCount() {
        return commentCount;
    }

    /**
     * Sets the number of comments on the post.
     *
     * @param commentCount the comment count to set
     */
    public void setCommentCount(int commentCount) {
        this.commentCount = commentCount;
    }
}