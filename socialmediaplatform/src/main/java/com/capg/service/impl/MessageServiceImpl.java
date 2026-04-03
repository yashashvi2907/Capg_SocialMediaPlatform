package com.capg.service.impl;

import com.capg.dto.MessageDTO;
import com.capg.entity.Message;
import com.capg.repository.MessageRepository;
import com.capg.service.MessageService;
import com.capg.exception.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for handling message-related operations.
 * Provides business logic for retrieving chat messages between users.
 */
@Service
public class MessageServiceImpl implements MessageService {

    /**
     * Repository layer for accessing message data.
     */
    private final MessageRepository messageRepository;

    /**
     * Constructor-based dependency injection for MessageRepository.
     * @param messageRepository repository for message data access
     */
    @Autowired
    public MessageServiceImpl(final MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Retrieves chat messages exchanged between two users.
     * @param user1Id unique identifier of the first user
     * @param user2Id unique identifier of the second user
     * @return list of MessageDTO representing chat messages
     * @throws ResourceNotFoundException if no messages are found
     */
    @Override
    public List<MessageDTO> getChatsBetweenUser(
            final Integer user1Id,
            final Integer user2Id) {

        final List<Message> messages =
                messageRepository.getChatsBetweenUser(user1Id, user2Id);

        if (messages.isEmpty()) {
            throw new ResourceNotFoundException("No messages found between users");
        }

        return messages.stream().map(msg -> {
            final MessageDTO dto = new MessageDTO();
            dto.setMessageID(msg.getMessageID());
            dto.setMessageText(msg.getMessageText());
            dto.setTimestamp(msg.getTimestamp());
            return dto;
        }).toList();
    }
}