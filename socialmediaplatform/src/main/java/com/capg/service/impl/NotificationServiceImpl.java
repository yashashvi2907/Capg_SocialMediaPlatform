package com.capg.service.impl;

import com.capg.Exception.InvalidUserIdException;
import com.capg.Exception.NotificationNotFoundException;
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

        if(userId <= 0){
            throw new InvalidUserIdException("Invalid userId");
        }
        List<NotificationDTO> notifications = notificationRepository.getUserNotifications(userId);

        if(notifications == null || notifications.isEmpty()){
            throw new NotificationNotFoundException("No Notifications found for userId: "+userId);
        }

        return notifications;
    }
}