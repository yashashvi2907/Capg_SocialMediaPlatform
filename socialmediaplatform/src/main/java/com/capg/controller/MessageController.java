package com.capg.controller;

import com.capg.dto.MessageDTO;

import com.capg.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for handling messaging operations.
 * Provides APIs to retrieve chat messages between users.
 */
@RestController
@RequestMapping("/messages")
public class MessageController {
    /**
     * Service layer for handling message-related operations.
     */
    private final MessageService messageService;

    /**
     * Constructor-based dependency injection for MessageService.
     *
     * @param messageService service layer for message operations
     */
    @Autowired
    public MessageController(final MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Retrieves chat messages exchanged between two users.
     * @param user1 unique identifier of the first user
     * @param user2 unique identifier of the second user
     * @return list of MessageDTO representing chat messages
     */
    @GetMapping("/{user1}/{user2}")
    public List<MessageDTO> getChats(@PathVariable Integer user1,
                                     @PathVariable Integer user2) {
        return messageService.getChatsBetweenUser(user1, user2);
    }
}