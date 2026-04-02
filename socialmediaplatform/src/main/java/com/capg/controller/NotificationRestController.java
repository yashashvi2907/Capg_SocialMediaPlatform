package com.capg.controller;

import com.capg.dto.NotificationDTO;
import com.capg.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationRestController {

    private final NotificationService service;

    public NotificationRestController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ResponseEntity<?> getNotifications(@PathVariable int userId) {

        List<NotificationDTO> list = service.getUserNotifications(userId);

        if (list.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No notifications found for user ID: " + userId);
        }

        return ResponseEntity.ok(list);
    }
}