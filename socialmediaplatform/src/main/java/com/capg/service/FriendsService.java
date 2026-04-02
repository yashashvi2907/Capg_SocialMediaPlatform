package com.capg.service;

import com.capg.dto.FriendsDTO;
import java.util.List;

public interface FriendsService {

    FriendsDTO addFriend(FriendsDTO dto);
    List<FriendsDTO> getAllFriends();

    FriendsDTO getFriendById(Integer id);
    void deleteFriend(Integer id);

    List<FriendsDTO> getPendingRequests(Integer userId);
    List<FriendsDTO> getAcceptedFriends(Integer userId);
    
    List<FriendsDTO> getAllPending();
    List<FriendsDTO> getAllAccepted();

}