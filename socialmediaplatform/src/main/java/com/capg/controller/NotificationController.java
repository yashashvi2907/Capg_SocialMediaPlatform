package com.capg.controller;

import com.capg.dto.NotificationDTO;
import com.capg.service.NotificationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller

public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/notifications/{userId}")
    public String getNotifications(@PathVariable int userId, Model model) {

        List<NotificationDTO> notifications = service.getUserNotifications(userId);
        model.addAttribute("notifications", notifications);

        return "notifications";
    }
}