package com.capg.repository;

import com.capg.dto.NotificationDTO;
import com.capg.entity.Notification;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Integer> {

    @Query("SELECT new com.capg.dto.NotificationDTO(" +
            "n.content, n.timestamp, u.username, u.email, u.profilePicture) " +
            "FROM Notification n JOIN n.user u WHERE u.userID = :userId")
    List<NotificationDTO> getUserNotifications(@Param("userId") int userId);
}