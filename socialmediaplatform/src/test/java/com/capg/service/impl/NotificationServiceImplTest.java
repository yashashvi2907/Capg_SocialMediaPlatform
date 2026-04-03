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
 *Unit tests for NotificationServiceImpl
 *Uses DTO-based repository approach
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

    public NotificationServiceImplTest() {
    }

    /**
     * Test for getting valid user notifications
     */

    @Test
    void testGetUserNotifications() {

        final int userId = 1;

        final NotificationDTO dto = new NotificationDTO(
                "New like on your post",
                LocalDateTime.now(),
                "john_doe",
                "john@example.com",
                new byte[]{1, 2, 3}
        );

        final List<NotificationDTO> mockList = Arrays.asList(dto);

        when(notificationRepository.getUserNotifications(userId))
                .thenReturn(mockList);

        final List<NotificationDTO> result = notificationService.getUserNotifications(userId);

        assertNotNull(result, "Result list should not be null");
        assertEquals(1, result.size(), "Result list should contain exactly one element");
        assertEquals("New like on your post",
                result.getFirst().getContent(),
                "Notification content should match expected message");


        verify(notificationRepository, times(1))
                .getUserNotifications(userId);
    }

    /**
     *     Test for no notifications found
     */
    @Test
    void testNoNotificationsFound() {

        final int userId = 1;

        when(notificationRepository.getUserNotifications(userId))
                .thenReturn(Collections.emptyList());

        final NotificationNotFoundException exception = assertThrows(
                NotificationNotFoundException.class,
                () -> notificationService.getUserNotifications(userId),
                "Expected NotificationNotFoundException when user has no notifications"
        );

        assertEquals("No Notifications found for userId: 1", exception.getMessage(),"Exception message should match expected text");

        verify(notificationRepository, times(1))
                .getUserNotifications(userId);
    }

    /**
     * Test notifications for Invalid user id
     */
    @Test
    void testInvalidUserId() {

        final InvalidUserIdException exception = assertThrows(
                InvalidUserIdException.class,
                () -> notificationService.getUserNotifications(0),
                "Expected InvalidUserIdException when userId is less than or equal to 0"
        );

        assertEquals("Invalid userId", exception.getMessage(),"Exception message should match expected text");

        verify(notificationRepository, never())
                .getUserNotifications(anyInt());
    }
}