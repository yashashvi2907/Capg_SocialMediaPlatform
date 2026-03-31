package com.capg.service.impl;

import com.capg.dto.MessageDTO;
import com.capg.entity.Message;
import com.capg.repository.MessageRepository;
import com.capg.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Override
    public List<MessageDTO> getChatsBetweenUser(Integer user1Id, Integer user2Id) {

        List<Message> messages = messageRepository.getChatsBetweenUser(user1Id, user2Id);

        return messages.stream().map(msg -> {
            MessageDTO dto = new MessageDTO();
            dto.setMessageID(msg.getMessageID());
            dto.setMessageText(msg.getMessageText());
            dto.setTimestamp(msg.getTimestamp());
            return dto;
        }).toList();
    }
}