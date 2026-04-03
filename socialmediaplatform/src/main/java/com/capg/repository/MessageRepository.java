package com.capg.repository;

import com.capg.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing Message entities.
 * Provides database operations related to user messages.
 */
public interface MessageRepository extends JpaRepository<Message, Integer> {

    /**
     * Retrieves chat messages exchanged between two users.
     * Fetches messages where either user is sender or receiver,
     * ordered by timestamp in ascending order.
     * @param user1 unique identifier of the first user
     * @param user2 unique identifier of the second user
     * @return list of Message entities representing the chat history
     */

    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender.userID = :user1 AND m.receiver.userID = :user2) " +
            "OR (m.sender.userID = :user2 AND m.receiver.userID = :user1) " +
            "ORDER BY m.timestamp ASC")
    List<Message> getChatsBetweenUser(@Param("user1") Integer user1,
                                      @Param("user2") Integer user2);
}