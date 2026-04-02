package com.capg.service.impl;

import com.capg.Exception.InvalidUserIdException;
import com.capg.Exception.NotificationNotFoundException;
import com.capg.dto.NotificationDTO;
import com.capg.repository.NotificationRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {

    @Mock
    private NotificationRepository notificationRepository;

    @InjectMocks
    private NotificationServiceImpl notificationService;

    //Positive Test Case
    @Test
    void testGetUserNotifications() {

        int userId = 1;

        NotificationDTO dto = new NotificationDTO(
                "New like on your post",
                LocalDateTime.now(),
                "john_doe",
                "john@example.com",
                new byte[]{1, 2, 3}
        );

        List<NotificationDTO> mockList = Arrays.asList(dto);

        when(notificationRepository.getUserNotifications(userId))
                .thenReturn(mockList);

        List<NotificationDTO> result = notificationService.getUserNotifications(userId);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("New like on your post", result.get(0).getContent());

        verify(notificationRepository, times(1))
                .getUserNotifications(userId);
    }

    //Negative Test Case
    @Test
    void testNoNotificationsFound() {

        int userId = 1;

        when(notificationRepository.getUserNotifications(userId))
                .thenReturn(Collections.emptyList());

        NotificationNotFoundException exception = assertThrows(
                NotificationNotFoundException.class,
                () -> notificationService.getUserNotifications(userId)
        );

        assertEquals("No Notifications found for userId: 1", exception.getMessage());

        verify(notificationRepository, times(1))
                .getUserNotifications(userId);
    }
}