package com.capg.controller;

import com.capg.dto.NotificationDTO;
import com.capg.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *  REST Controller for managing user notifications.
 *  This controller provides endpoints to fetch notifications
 *  associated with a specific user.
 */
@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    private final NotificationService service;

    /**
     * Constructor-based dependency injection for NotificationService.
     *
     * @param service the notification service layer
     */
    public NotificationRestController(NotificationService service) {
        this.service = service;
    }
    /**
     * Fetches all notifications for a given user ID.
     *
     * <p>
     * Endpoint: GET /api/notifications/{userId}
     * </p>
     *
     * @param userId the unique identifier of the user
     * @return ResponseEntity containing:
     * <ul>
     *     <li>200 OK with list of NotificationDTO if notifications exist</li>
     *     <li>404 NOT FOUND with message if no notifications are found</li>
     * </ul>
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getNotifications(@PathVariable int userId) {

        final List<NotificationDTO> list = service.getUserNotifications(userId);

        if (list.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No notifications found for user ID: " + userId);
        }

        return ResponseEntity.ok(list);
    }
}