package com.capg.controller;

import com.capg.dto.MessageDTO;

import com.capg.service.MessageService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/{user1}/{user2}")
    public List<MessageDTO> getChats(@PathVariable Integer user1,
                                     @PathVariable Integer user2) {
        return messageService.getChatsBetweenUser(user1, user2);
    }
}