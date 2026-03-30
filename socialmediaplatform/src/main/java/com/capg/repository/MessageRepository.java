package com.capg.repository;

import com.capg.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Integer> {

    @Query("SELECT m FROM Message m WHERE " +
            "(m.sender.userID = :user1 AND m.receiver.userID = :user2) " +
            "OR (m.sender.userID = :user2 AND m.receiver.userID = :user1) " +
            "ORDER BY m.timestamp ASC")
    List<Message> getChatsBetweenUser(Integer user1, Integer user2);
}