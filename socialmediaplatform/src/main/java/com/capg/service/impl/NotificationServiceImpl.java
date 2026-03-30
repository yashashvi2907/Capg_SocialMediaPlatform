package com.capg.service.impl;

import com.capg.dto.NotificationDTO;
import com.capg.repository.NotificationRepository;
import com.capg.service.NotificationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;


    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public List<NotificationDTO> getUserNotifications(int userId) {

        List<NotificationDTO> notifications = notificationRepository.getUserNotifications(userId);


        return notifications;
    }
}