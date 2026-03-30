package com.capg.service;

import com.capg.dto.MessageDTO;
import java.util.List;

public interface MessageService {

    List<MessageDTO> getChatsBetweenUser(Integer user1Id, Integer user2Id);
}