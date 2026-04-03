package com.capg.service.impl;

import com.capg.exception.InvalidUserIdException;
import com.capg.exception.NotificationNotFoundException;
import com.capg.dto.NotificationDTO;
import com.capg.repository.NotificationRepository;
import com.capg.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for handling notification-related operations.
 * <p>
 * Provides business logic for fetching user notifications with
 * proper validation and exception handling.
 * </p>
 */
@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository nRepo;

    /**
     * Constructor-based dependency injection for NotificationRepository.
     *
     * @param notificationRepo the repository for notification data access
     */
    public NotificationServiceImpl(NotificationRepository notificationRepo) {
        this.nRepo = notificationRepo;
    }

    /**
     * Retrieves notifications for a given user.
     *
     * @param userId the unique identifier of the user
     * @return list of NotificationDTO containing user notifications
     * @throws InvalidUserIdException        if userId is invalid (<= 0)
     * @throws NotificationNotFoundException if no notifications are found
     */
    @Override
    public List<NotificationDTO> getUserNotifications(int userId) {

        if(userId <= 0){
            throw new InvalidUserIdException("Invalid userId");
        }

        final List<NotificationDTO> notifications = nRepo.getUserNotifications(userId);

        if(notifications == null || notifications.isEmpty()){
            throw new NotificationNotFoundException("No Notifications found for userId: "+userId);
        }

        return notifications;
    }
}