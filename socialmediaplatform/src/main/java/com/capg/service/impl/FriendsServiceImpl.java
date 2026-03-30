
package com.capg.service.impl;

import com.capg.dto.FriendsDTO;
import com.capg.entity.Friends;
import com.capg.entity.User;
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
        return repo.findAll().stream().map(this::convertToDTO).collect(Collectors.toList());
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

        User user = userRepo.findById(userId).orElse(null);

        return repo.findByUser2AndStatus(user, "pending")
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<FriendsDTO> getAcceptedFriends(Integer userId) {

        User user = userRepo.findById(userId).orElse(null);

        return repo.findByUser1OrUser2(user, user)
                .stream()
                .filter(f -> f.getStatus().equalsIgnoreCase("accepted"))
                .map(this::convertToDTO)
                .collect(Collectors.toList());
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
