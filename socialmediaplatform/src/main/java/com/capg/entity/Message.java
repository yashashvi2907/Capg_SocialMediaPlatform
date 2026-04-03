package com.capg.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 * Entity representing a message exchanged between users.
 * Maps to the "messages" table in the database.
 */
@Entity
@Table(name = "messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	/**
	 * Unique identifier of the message.
	 */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageID;

	/**
	 * Content of the message.
	 */
	@Column(name="message_text")
	private String messageText;

	/**
	 * Timestamp when the message was created.
	 */
	private LocalDateTime timestamp;

	/**
	 * Sender of the message.
	 */
	@ManyToOne
	@JoinColumn(name = "senderID")
	private User sender;

	/**
	 * Receiver of the message.
	 */
	@ManyToOne
	@JoinColumn(name = "receiverID")
	private User receiver;

	public Integer getMessageID() { return messageID; }

	public void setMessageID(final Integer messageID) {
		this.messageID = messageID;
	}

	public String getMessageText() { return messageText; }

	public void setMessageText(final String messageText) {
		this.messageText = messageText;
	}

	public LocalDateTime getTimestamp() { return timestamp; }

	public void setTimestamp(final LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public User getSender() { return sender; }

	public void setSender(final User sender) {
		this.sender = sender;
	}

	public User getReceiver() { return receiver; }

	public void setReceiver(final User receiver) {
		this.receiver = receiver;
	}
}