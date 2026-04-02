package com.capg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "`Groups`")
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer groupID;
      @Column(name="groupName")
	private String groupName;

	@ManyToOne
	@JoinColumn(name = "adminID")
	private User admin;

	public Integer getGroupID() {
		return groupID;
	}

	public void setGroupID(Integer groupID) {
		this.groupID = groupID;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public User getAdmin() {
		return admin;
	}

	public void setAdmin(User admin) {
		this.admin = admin;
	}
}