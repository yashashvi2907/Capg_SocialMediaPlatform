package com.capg.service;

import com.capg.dto.NotificationDTO;
import java.util.List;

public interface NotificationService {

    List<NotificationDTO> getUserNotifications(int userId);
}