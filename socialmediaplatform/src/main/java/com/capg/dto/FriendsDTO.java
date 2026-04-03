package com.capg.dto;

/**
 * FriendsDTO (Data Transfer Object)
 * 
 * This class is used to transfer Friends data between layers
 * (Controller ↔ Service ↔ Client) without exposing the actual entity.
 * 
 * It contains basic information about friendship:
 * - Friendship ID
 * - Sender User ID
 * - Receiver User ID
 * - Status of friendship (pending / accepted)
 */
public class FriendsDTO {

    /**
     * Unique ID representing friendship
     */
    private Integer friendshipID;

    /**
     * ID of the sender (user who sent request)
     */
    private Integer userID1;

    /**
     * ID of the receiver (user who receives request)
     */
    private Integer userID2;

    /**
     * Status of friendship (pending / accepted)
     */
    private String status;

    /**
     * Default Constructor
     */
    public FriendsDTO() {}

    /**
     * Parameterized Constructor
     * 
     * @param friendshipID unique friendship ID
     * @param userID1 sender user ID
     * @param userID2 receiver user ID
     * @param status friendship status
     */
    public FriendsDTO(Integer friendshipID, Integer userID1, Integer userID2, String status) {
        this.friendshipID = friendshipID;
        this.userID1 = userID1;
        this.userID2 = userID2;
        this.status = status;
    }

    /**
     * Get Friendship ID
     * @return friendship ID
     */
    public Integer getFriendshipID() { return friendshipID; }

    /**
     * Set Friendship ID
     * @param friendshipID unique ID
     */
    public void setFriendshipID(Integer friendshipID) { this.friendshipID = friendshipID; }

    /**
     * Get Sender User ID
     * @return userID1
     */
    public Integer getUserID1() { return userID1; }

    /**
     * Set Sender User ID
     * @param userID1 sender ID
     */
    public void setUserID1(Integer userID1) { this.userID1 = userID1; }

    /**
     * Get Receiver User ID
     * @return userID2
     */
    public Integer getUserID2() { return userID2; }

    /**
     * Set Receiver User ID
     * @param userID2 receiver ID
     */
    public void setUserID2(Integer userID2) { this.userID2 = userID2; }

    /**
     * Get Friendship Status
     * @return status (pending / accepted)
     */
    public String getStatus() { return status; }

    /**
     * Set Friendship Status
     * @param status friendship status
     */
    public void setStatus(String status) { this.status = status; }
}