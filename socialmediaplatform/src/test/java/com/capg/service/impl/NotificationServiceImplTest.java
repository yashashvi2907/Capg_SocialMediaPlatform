package com.capg.service.impl;

import com.capg.exception.InvalidUserIdException;
import com.capg.exception.NotificationNotFoundException;
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

/**
 *
 */
@ExtendWith(MockitoExtension.class)
class NotificationServiceImplTest {
    /**
     * Mocked repository for handling Notification entity persistence operations.
     * Used to simulate database interactions in unit tests.
     */
    @Mock
    private NotificationRepository notificationRepository;

    /**
     * Service implementation under test.
     * Injects the mocked dependencies to validate NotificationService behavior.
     */
    @InjectMocks
    private NotificationServiceImpl notificationService;

    //Test for getting valid user notifications
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

    //Test for no notifications found
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

    //Test notifications for Invalid user id
    @Test
    void testInvalidUserId() {

        InvalidUserIdException exception = assertThrows(
                InvalidUserIdException.class,
                () -> notificationService.getUserNotifications(0)
        );

        assertEquals("Invalid userId", exception.getMessage());

        verify(notificationRepository, never())
                .getUserNotifications(anyInt());
    }
}