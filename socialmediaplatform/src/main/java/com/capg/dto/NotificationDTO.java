package com.capg.dto;

import java.time.LocalDateTime;
import java.util.Base64;

/**
 * Data Transfer Object (DTO) for transferring notification details.
 * <p>
 * This class encapsulates notification data along with associated user
 * information such as username, email, and profile picture.
 * </p>
 */
public class NotificationDTO {

    private String content;
    private LocalDateTime timestamp;
    private String username;
    private String email;
    private byte[] profilePicture;

    /**
     * Default constructor.
     */
    public NotificationDTO(){}

    /**
     * Parameterized constructor to initialize all fields.
     *
     * @param content         the notification message/content
     * @param timestamp       the time when the notification was created
     * @param username        the username of the associated user
     * @param email           the email of the associated user
     * @param profilePicture  the profile picture as a byte array
     */
    public NotificationDTO(String content, LocalDateTime timestamp,
                           String username, String email, byte[] profilePicture) {
        this.content = content;
        this.timestamp = timestamp;
        this.username = username;
        this.email = email;
        this.profilePicture = (profilePicture != null) ? profilePicture.clone() : null;
    }

    /**
     * Gets the notification content.
     *
     * @return the notification message
     */
    public String getContent() { return content; }

    /**
     * Gets the timestamp of the notification.
     *
     * @return the notification timestamp
     */
    public LocalDateTime getTimestamp() { return timestamp; }

    /**
     * Gets the username associated with the notification.
     *
     * @return the username
     */
    public String getUsername() { return username; }

    /**
     * Gets the email associated with the notification.
     *
     * @return the email
     */
    public String getEmail() { return email; }

    /**
     * Returns the profile picture encoded as a Base64 string.
     * <p>
     * This is useful for sending image data in API responses
     * (e.g., JSON) without binary issues.
     * </p>
     *
     * @return Base64 encoded string of profile picture, or null if not available
     */
    public String getProfilePicture() {
        if (profilePicture == null || profilePicture.length == 0) {
            return null;
        }
        return Base64.getEncoder().encodeToString(profilePicture);
    }
}