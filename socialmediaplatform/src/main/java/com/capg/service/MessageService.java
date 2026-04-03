package com.capg.service;

import com.capg.dto.MessageDTO;
import java.util.List;

/**
 * Service interface for handling message-related operations.
 * Defines business logic for retrieving chat messages between users.
 */
public interface MessageService {

    /**
     * Retrieves chat messages exchanged between two users.
     *
     * @param user1Id unique identifier of the first user
     * @param user2Id unique identifier of the second user
     * @return list of MessageDTO representing chat messages
     */
    List<MessageDTO> getChatsBetweenUser(Integer user1Id,
                                         Integer user2Id);
}