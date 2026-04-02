
package com.capg.service.impl;

import com.capg.dto.FriendsDTO;
import com.capg.entity.Friends;
import com.capg.entity.User;
import com.capg.exception.FriendNotFoundException;
import com.capg.exception.NoDataFoundException;
import com.capg.repository.IFriendsRepo;
import com.capg.repository.UserRepository;
import com.capg.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private IFriendsRepo repo;

    @Autowired
    private UserRepository userRepo;

    @Override
    public FriendsDTO addFriend(FriendsDTO dto) {

        User user1 = userRepo.findById(dto.getUserID1()).orElse(null);
        User user2 = userRepo.findById(dto.getUserID2()).orElse(null);

        Friends f = new Friends();
        f.setUser1(user1);
        f.setUser2(user2);
        f.setStatus(dto.getStatus());

        return convertToDTO(repo.save(f));
    }

    @Override
    public List<FriendsDTO> getAllFriends() {

        List<FriendsDTO> list = repo.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NoDataFoundException("No friends available");
        }

        return list;
    }

    @Override
    public FriendsDTO getFriendById(Integer id) {
        Friends f = repo.findById(id).orElseThrow(() -> new RuntimeException("Friend not found"));
        return convertToDTO(f);
    }

    @Override
    public void deleteFriend(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<FriendsDTO> getPendingRequests(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new FriendNotFoundException("User not found with ID: " + userId));

        List<FriendsDTO> list = repo.findByUser2AndStatus(user, "pending")
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NoDataFoundException("No pending requests found");
        }

        return list;
    }
    
    
    @Override
    public List<FriendsDTO> getAcceptedFriends(Integer userId) {

        User user = userRepo.findById(userId)
                .orElseThrow(() -> new FriendNotFoundException("User not found with ID: " + userId));

        List<FriendsDTO> list = repo.findByUser1OrUser2(user, user)
                .stream()
                .filter(f -> f.getStatus().equalsIgnoreCase("accepted"))
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NoDataFoundException("No accepted friends found");
        }

        return list;
    }
    
    
    @Override
    public List<FriendsDTO> getAllPending() {

        List<FriendsDTO> list = repo.findByStatus("pending")
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NoDataFoundException("No pending requests in system");
        }

        return list;
    }

    @Override
    public List<FriendsDTO> getAllAccepted() {

        List<FriendsDTO> list = repo.findByStatus("accepted")
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        if (list.isEmpty()) {
            throw new NoDataFoundException("No accepted friendships in system");
        }

        return list;
    }

    // CONVERT ENTITY → DTO
    private FriendsDTO convertToDTO(Friends f) {
        return new FriendsDTO(
                f.getFriendshipID(),
                f.getUser1().getUserID(),
                f.getUser2().getUserID(),
                f.getStatus()
        );
    }
}
