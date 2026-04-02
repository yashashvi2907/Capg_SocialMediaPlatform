package com.capg.service.impl;

import com.capg.dto.MessageDTO;
import com.capg.entity.Message;
import com.capg.repository.MessageRepository;
import com.capg.exception.ResourceNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageServiceImpl messageService;

    // Positive Test Case
    @Test
    void testGetChatsBetweenUser_Success() {

        Message msg = new Message();
        msg.setMessageID(1);
        msg.setMessageText("Hello");
        msg.setTimestamp(LocalDateTime.now());

        when(messageRepository.getChatsBetweenUser(1, 2))
                .thenReturn(List.of(msg));

        List<MessageDTO> result =
                messageService.getChatsBetweenUser(1, 2);

        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getMessageText());
    }

    //  Negative Test Case (Exception)
    @Test
    void testGetChatsBetweenUser_NoMessages() {

        when(messageRepository.getChatsBetweenUser(1, 2))
                .thenReturn(List.of());

        ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> messageService.getChatsBetweenUser(1, 2)
        );

        assertEquals("No messages found between users", ex.getMessage());
    }
}