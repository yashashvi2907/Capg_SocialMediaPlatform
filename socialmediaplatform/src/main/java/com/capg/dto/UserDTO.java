package com.capg.dto;

/**
 * Data Transfer Object for User entity.
 * Used to transfer user data between layers.
 */
public class UserDTO {

    /**
     * Unique identifier of the user.
     */
    private Integer userID;

    /**
     * Username of the user.
     */
    private String username;

    /**
     * Email of the user.
     */
    private String email;

    /**
     * Profile picture of the user.
     */
    private byte[] profilePicture;

    /**
     * Default constructor.
     */
    public UserDTO() {
        // default constructor
    }

    public Integer getUserID() {
        return userID;
    }

    public void setUserID(final Integer userID) {
        this.userID = userID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public byte[] getProfilePicture() {
        return profilePicture != null ? profilePicture.clone() : null;
    }

    public void setProfilePicture(final byte[] profilePicture) {
            this.profilePicture = profilePicture;
    }
}