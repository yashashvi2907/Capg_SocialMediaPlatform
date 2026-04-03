package com.capg.dto;

import java.time.LocalDateTime;

/**
 * Data Transfer Object for Message entity.
 * Used to transfer message data between layers.
 */
public class MessageDTO {

    /**
     * Unique identifier of the message.
     */
    private Integer messageID;

    /**
     * Content of the message.
     */
    private String messageText;

    /**
     * Timestamp when the message was created.
     */
    private LocalDateTime timestamp;

    /**
     * Default constructor.
     * Initializes an empty MessageDTO object.
     */
    public MessageDTO() {
        // default constructor
    }

    /**
     * Gets the message ID.
     * @return message ID
     */
    public Integer getMessageID() {
        return messageID;
    }

    /**
     * Sets the message ID.
     * @param messageID unique identifier of the message
     */
    public void setMessageID(final Integer messageID) {
        this.messageID = messageID;
    }

    /**
     * Gets the message text.
     * @return message content
     */
    public String getMessageText() {
        return messageText;
    }

    /**
     * Sets the message text.
     * @param messageText content of the message
     */
    public void setMessageText(final String messageText) {
        this.messageText = messageText;
    }

    /**
     * Gets the message timestamp.
     * @return timestamp of the message
     */
    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the message timestamp.
     * @param timestamp time when the message was created
     */
    public void setTimestamp(final LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}