package com.capg.service;

import com.capg.dto.NotificationDTO;
import java.util.List;

/**
 * Service interface for managing notification-related operations.
 * <p>
 * Defines business operations for retrieving notifications
 * associated with users.
 * </p>
 */
public interface NotificationService {

    /**
     * Retrieves all notifications for a specific user.
     *
     * @param userId the unique identifier of the user
     * @return list of NotificationDTO containing notification details
     */
    List<NotificationDTO> getUserNotifications(int userId);
}