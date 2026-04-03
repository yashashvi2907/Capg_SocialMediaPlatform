package com.capg.repository;

import com.capg.entity.Friends;
import com.capg.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * IFriendsRepo (Repository Interface)
 * 
 * This interface is used for performing database operations
 * related to Friends entity.
 * 
 * It extends JpaRepository, which provides:
 * - CRUD operations (save, delete, findById, findAll)
 * - Pagination and sorting support
 * 
 * Custom query methods are defined using Spring Data JPA method naming.
 */
public interface IFriendsRepository extends JpaRepository<Friends, Integer> {

    /**
     * Fetch all pending friend requests where the given user is the receiver
     * 
     * @param user2 receiver user
     * @param status status of request (pending)
     * @return list of pending friend requests
     */
    List<Friends> findByUser2AndStatus(User user2, String status);

    /**
     * Fetch all friendships where the user is either sender or receiver
     * 
     * @param user1 sender user
     * @param user2 receiver user
     * @return list of all friendships
     */
    List<Friends> findByUser1OrUser2(User user1, User user2);

    /**
     * Fetch all friendships based on status
     * 
     * @param status status of friendship (pending / accepted)
     * @return list of friendships
     */
    List<Friends> findByStatus(String status);
}