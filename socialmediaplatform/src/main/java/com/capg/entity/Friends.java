package com.capg.entity;

import jakarta.persistence.*;

/**
 * Friends Entity
 * 
 * This class represents the friendship relationship between two users
 * in the Social Media Platform.
 * 
 * Table Name: friends
 * 
 * Description:
 * - Stores friend requests and accepted friendships
 * - Each record represents a connection between two users
 * 
 * Fields:
 * - friendshipID : Unique identifier (Primary Key)
 * - user1        : Sender of friend request
 * - user2        : Receiver of friend request
 * - status       : Status of friendship (pending / accepted)
 * 
 * Relationships:
 * - Many-to-One with User (user1)
 * - Many-to-One with User (user2)
 * 
 * Workflow:
 * - User1 sends request → status = "pending"
 * - User2 accepts → status = "accepted"
 */
@Entity
@Table(name = "friends")
public class Friends {

    /**
     * Primary Key - Unique ID for each friendship
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer friendshipID;

    /**
     * Status of friendship (pending / accepted)
     */
    private String status;

    /**
     * Sender of friend request
     */
    @ManyToOne
    @JoinColumn(name = "userID1")
    private User user1;

    /**
     * Receiver of friend request
     */
    @ManyToOne
    @JoinColumn(name = "userID2")
    private User user2;

    /**
     * Get Friendship ID
     * @return friendship ID
     */
    public Integer getFriendshipID() {
        return friendshipID;
    }

    /**
     * Set Friendship ID
     * @param friendshipID unique ID
     */
    public void setFriendshipID(Integer friendshipID) {
        this.friendshipID = friendshipID;
    }

    /**
     * Get Friendship Status
     * @return status (pending / accepted)
     */
    public String getStatus() {
        return status;
    }

    /**
     * Set Friendship Status
     * @param status friendship status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Get Sender User
     * @return user1 (sender)
     */
    public User getUser1() {
        return user1;
    }

    /**
     * Set Sender User
     * @param user1 sender user
     */
    public void setUser1(User user1) {
        this.user1 = user1;
    }

    /**
     * Get Receiver User
     * @return user2 (receiver)
     */
    public User getUser2() {
        return user2;
    }

    /**
     * Set Receiver User
     * @param user2 receiver user
     */
    public void setUser2(User user2) {
        this.user2 = user2;
    }
}