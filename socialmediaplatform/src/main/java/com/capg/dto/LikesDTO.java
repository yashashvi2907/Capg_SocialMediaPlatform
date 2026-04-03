package com.capg.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Likes entity.
 * Represents like details associated with a user and a post.
 */
public class LikesDTO {

    /**
     * Unique identifier for the like.
     */
    private Integer likeID;

    /**
     * ID of the user who liked the post.
     */
    private Integer userID;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * ID of the post that was liked.
     */
    private Integer postID;

    /**
     * Timestamp when the like was created.
     */
    private LocalDateTime timestamp;

    /**
     * @return like ID
     */
    public Integer getLikeID() {
        return likeID;
    }

    /**
     * @param likeID sets like ID
     */
    public void setLikeID(Integer likeID) {
        this.likeID = likeID;
    }

    /**
     * @return user ID
     */
    public Integer getUserID() {
        return userID;
    }

    /**
     * @param userID sets user ID
     */
    public void setUserID(Integer userID) {
        this.userID = userID;
    }

    /**
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username sets username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return post ID
     */
    public Integer getPostID() {
        return postID;
    }

    /**
     * @param postID sets post ID
     */
    public void setPostID(Integer postID) {
        this.postID = postID;
    }

    /**
     * @return timestamp of like
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp sets timestamp
     */
    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}