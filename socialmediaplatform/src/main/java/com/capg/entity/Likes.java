package com.capg.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity class representing a Like on a post.
 */
@Entity
@Table(name = "Likes")
public class Likes {

	/** Unique identifier for the like. */
	@Id
	@Column(name = "likeID")
	private Integer likeID;

	/** Timestamp when the like was created. */
	@Column(name = "timestamp")
	private LocalDateTime timestamp;

	/** User who liked the post. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "userID")
	private User user;

	/** Post that is liked. */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "postID")
	private Post post;

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
	 * @return timestamp
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

	/**
	 * @return user who liked
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user sets user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return liked post
	 */
	public Post getPost() {
		return post;
	}

	/**
	 * @param post sets post
	 */
	public void setPost(Post post) {
		this.post = post;
	}
}