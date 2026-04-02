package com.capg.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="messages")
@NoArgsConstructor
@AllArgsConstructor
public class Message {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer messageID;

	private String messageText;
	private LocalDateTime timestamp;

	@ManyToOne
	@JoinColumn(name = "senderID")
	private User sender;

	@ManyToOne
	@JoinColumn(name = "receiverID")
	private User receiver;

	public Integer getMessageID() { return messageID; }
	public void setMessageID(Integer messageID) { this.messageID = messageID; }

	public String getMessageText() { return messageText; }
	public void setMessageText(String messageText) { this.messageText = messageText; }

	public LocalDateTime getTimestamp() { return timestamp; }
	public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

	public User getSender() { return sender; }
	public void setSender(User sender) { this.sender = sender; }

	public User getReceiver() { return receiver; }
	public void setReceiver(User receiver) { this.receiver = receiver; }

}