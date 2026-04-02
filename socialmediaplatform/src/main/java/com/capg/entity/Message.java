package com.capg.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name="messages")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = {"sender", "receiver"})
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

}