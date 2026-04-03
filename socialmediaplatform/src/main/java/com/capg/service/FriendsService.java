package com.capg.service;

import com.capg.dto.FriendsDTO;
import java.util.List;

/**
 * FriendsService (Service Interface)
 * 
 * This interface defines all business operations related to Friends module
 * in the Social Media Platform.
 * 
 * It acts as a bridge between Controller and Repository layers.
 * 
 * Responsibilities:
 * - Add new friend request
 * - Fetch all friends
 * - Fetch friend by ID
 * - Delete friendship
 * - Fetch pending and accepted friend requests
 */
public interface FriendsService {

    /**
     * Add a new friend request
     * 
     * @param dto FriendsDTO containing user details and status
     * @return saved FriendsDTO object
     */
    FriendsDTO addFriend(FriendsDTO dto);

    /**
     * Get all friends in the system
     * 
     * @return list of all friends
     */
    List<FriendsDTO> getAllFriends();

    /**
     * Get a specific friend by ID
     * 
     * @param id friendship ID
     * @return FriendsDTO object
     */
    FriendsDTO getFriendById(Integer id);

    /**
     * Delete a friendship by ID
     * 
     * @param id friendship ID
     */
    void deleteFriend(Integer id);

    /**
     * Get all pending friend requests for a specific user
     * 
     * @param userId ID of the user
     * @return list of pending requests
     */
    List<FriendsDTO> getPendingRequests(Integer userId);

    /**
     * Get all accepted friends for a specific user
     * 
     * @param userId ID of the user
     * @return list of accepted friends
     */
    List<FriendsDTO> getAcceptedFriends(Integer userId);
    
    /**
     * Get all pending friend requests in the system
     * 
     * @return list of all pending friendships
     */
    List<FriendsDTO> getAllPending();

    /**
     * Get all accepted friendships in the system
     * 
     * @return list of all accepted friendships
     */
    List<FriendsDTO> getAllAccepted();
    
    List<FriendsDTO> getMutualFriends(Integer user1Id, Integer user2Id);
}