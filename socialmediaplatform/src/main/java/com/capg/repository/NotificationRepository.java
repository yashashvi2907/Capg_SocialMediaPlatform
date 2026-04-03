package com.capg.repository;

import com.capg.dto.NotificationDTO;
import com.capg.entity.Notification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * Repository interface for managing Notification entities.
 * <p>
 * Extends JpaRepository to provide CRUD operations and
 * custom query methods for notification-related data.
 * </p>
 */
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    /**
     * Fetches notifications for a specific user.
     * <p>
     * This query joins Notification with User entity and directly maps
     * the result into NotificationDTO using a constructor expression.
     * </p>
     *
     * @param userId the unique identifier of the user
     * @return list of NotificationDTO containing notification details
     */
    @Query("SELECT new com.capg.dto.NotificationDTO(" +
            "n.content, n.timestamp, u.username, u.email, u.profilePicture) " +
            "FROM Notification n JOIN n.user u WHERE u.userID = :userId")
    List<NotificationDTO> getUserNotifications(@Param("userId") int userId);
}