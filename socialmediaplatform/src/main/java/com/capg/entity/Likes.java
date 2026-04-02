package com.capg.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "Likes")
public class Likes {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "likeID")
	private Integer likeID;

	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID")
	private User user;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postID")
	private Post post;

	public Integer getLikeID() {
		return likeID;
	}
	
    public void setLikeID(Integer likeID) {
        this.likeID = likeID;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

	public void setPost(Post post) {
		this.post = post;
	}
}