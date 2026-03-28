//package com.cg.entity;
//
//import jakarta.persistence.*;
//
//@Entity
//@Table(name = "friends")
//public class Friends {
//
//    @Id
//    @Column(name = "friendshipID")
//    int friendshipID;
//
//    @Column(name = "status")
//    String status;
//
//    @ManyToOne
//    @JoinColumn(name = "userID1")
//    User user1;
//
//    @ManyToOne
//    @JoinColumn(name = "userID2")
//    User user2;
//
//    public Friends(int friendshipID, String status, User user1, User user2) {
//        this.friendshipID = friendshipID;
//        this.status = status;
//        this.user1 = user1;
//        this.user2 = user2;
//    }
//
//    public Friends() {}
//
//	public int getFriendshipID() {
//		return friendshipID;
//	}
//
//	public void setFriendshipID(int friendshipID) {
//		this.friendshipID = friendshipID;
//	}
//
//	public String getStatus() {
//		return status;
//	}
//
//	public void setStatus(String status) {
//		this.status = status;
//	}
//
//	public User getUser1() {
//		return user1;
//	}
//
//	public void setUser1(User user1) {
//		this.user1 = user1;
//	}
//
//	public User getUser2() {
//		return user2;
//	}
//
//	public void setUser2(User user2) {
//		this.user2 = user2;
//	}
//
//	@Override
//	public String toString() {
//		return "Friends [friendshipID=" + friendshipID + ", status=" + status + ", user1=" + user1 + ", user2=" + user2
//				+ "]";
//	}
//
//    
//}


package com.capg.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "friends")
public class Friends {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer friendshipID;

	private String status;

	@ManyToOne
	@JoinColumn(name = "userID1")
	private User user1;

	@ManyToOne
	@JoinColumn(name = "userID2")
	private User user2;

	public Integer getFriendshipID() {
		return friendshipID;
	}

	public void setFriendshipID(Integer friendshipID) {
		this.friendshipID = friendshipID;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public User getUser1() {
		return user1;
	}

	public void setUser1(User user1) {
		this.user1 = user1;
	}

	public User getUser2() {
		return user2;
	}

	public void setUser2(User user2) {
		this.user2 = user2;
	}


}