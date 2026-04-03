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

/**
 * Unit test class for MessageServiceImpl.
 * Verifies behavior of message retrieval logic using Mockito.
 */
@ExtendWith(MockitoExtension.class)
class MessageServiceImplTest {

    /**
     * Mocked MessageRepository dependency.
     */
    @Mock
    private MessageRepository messageRepository;

    /**
     * MessageServiceImpl instance with injected mocks.
     */
    @InjectMocks
    private MessageServiceImpl messageService;

    /**
     * Tests successful retrieval of chat messages between two users.
     */
    @Test
    void testGetChatsBetweenUser_Success() {

        final Message msg = new Message();
        msg.setMessageID(1);
        msg.setMessageText("Hello");
        msg.setTimestamp(LocalDateTime.now());

        when(messageRepository.getChatsBetweenUser(1, 2))
                .thenReturn(List.of(msg));

        final List<MessageDTO> result =
                messageService.getChatsBetweenUser(1, 2);

        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getMessageText());
    }

    /**
     * Tests scenario where no messages exist between users.
     * Expects ResourceNotFoundException to be thrown.
     */
    @Test
    void testGetChatsBetweenUser_NoMessages() {

        when(messageRepository.getChatsBetweenUser(1, 2))
                .thenReturn(List.of());

        final ResourceNotFoundException ex = assertThrows(
                ResourceNotFoundException.class,
                () -> messageService.getChatsBetweenUser(1, 2)
        );

        assertEquals("No messages found between users", ex.getMessage());
    }
}