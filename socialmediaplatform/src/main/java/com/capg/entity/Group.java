package com.capg.entity;

import jakarta.persistence.*;

/**
 * Entity class representing a Group in the system.
 */
@Entity
@Table(name = "`Groups`")
public class Group {

	/** Unique identifier for the group. */
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupID;

	/** Name of the group. */
	@Column(name = "groupName")
	private String groupName;

	/** User who is the admin of the group. */
	@ManyToOne
	@JoinColumn(name = "adminID")
	private User admin;

	/**
	 * @return group ID
	 */
	public Integer getGroupID() {
		return groupID;
	}

	/**
	 * @param groupID sets group ID
	 */
	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	/**
	 * @return group name
	 */
	public String getGroupName() {
		return groupName;
	}

	/**
	 * @param groupName sets group name
	 */
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	/**
	 * @return admin user
	 */
	public User getAdmin() {
		return admin;
	}

	/**
	 * @param admin sets admin user
	 */
	public void setAdmin(User admin) {
		this.admin = admin;
	}
}