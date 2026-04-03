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

/**
 * FriendsServiceImpl (Service Implementation)
 * 
 * This class implements FriendsService interface and contains
 * the business logic for managing friendships in the system.
 * 
 * Responsibilities:
 * - Add friend request
 * - Fetch all friends
 * - Fetch friend by ID
 * - Delete friendship
 * - Fetch pending and accepted requests
 * 
 * It interacts with Repository layer to perform database operations.
 */
@Service
public class FriendsServiceImpl implements FriendsService {

    /**
     * Repository for Friends entity
     */
    @Autowired
    private IFriendsRepo repo;

    /**
     * Repository for User entity
     */
    @Autowired
    private UserRepository userRepo;

    /**
     * Add a new friend request
     * 
     * @param dto FriendsDTO containing sender, receiver, and status
     * @return saved FriendsDTO
     */
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

    /**
     * Fetch all friendships in the system
     * 
     * @return list of all friends
     * @throws NoDataFoundException if no data exists
     */
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

    /**
     * Fetch friend by ID
     * 
     * @param id friendship ID
     * @return FriendsDTO object
     * @throws FriendNotFoundException if not found
     */
    @Override
    public FriendsDTO getFriendById(Integer id) {
        Friends f = repo.findById(id)
                .orElseThrow(() -> new FriendNotFoundException("Friend not found with ID: " + id));
        return convertToDTO(f);
    }

    /**
     * Delete a friendship by ID
     * 
     * @param id friendship ID
     */
    @Override
    public void deleteFriend(Integer id) {
        repo.deleteById(id);
    }

    /**
     * Fetch pending friend requests for a user
     * 
     * @param userId user ID
     * @return list of pending requests
     * @throws FriendNotFoundException if user not found
     * @throws NoDataFoundException if no requests exist
     */
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

    /**
     * Fetch accepted friends for a user
     * 
     * @param userId user ID
     * @return list of accepted friends
     * @throws FriendNotFoundException if user not found
     * @throws NoDataFoundException if no friends exist
     */
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

    /**
     * Fetch all pending friend requests in the system
     * 
     * @return list of pending friendships
     * @throws NoDataFoundException if no data exists
     */
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

    /**
     * Fetch all accepted friendships in the system
     * 
     * @return list of accepted friendships
     * @throws NoDataFoundException if no data exists
     */
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

    /**
     * Convert Friends Entity to FriendsDTO
     * 
     * @param f Friends entity
     * @return FriendsDTO object
     */
    private FriendsDTO convertToDTO(Friends f) {
        return new FriendsDTO(
                f.getFriendshipID(),
                f.getUser1().getUserID(),
                f.getUser2().getUserID(),
                f.getStatus()
        );
    }
}