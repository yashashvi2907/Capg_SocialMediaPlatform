package com.capg.controller;

import com.capg.dto.FriendsDTO;
import com.capg.exception.UnauthorizedException;
import com.capg.security.JwtUtil;
import com.capg.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FriendsController
 * 
 * This controller handles all REST APIs related to Friends functionality
 * in the Social Media Platform.
 * 
 * Responsibilities:
 * - Fetch pending friend requests
 * - Fetch accepted friends
 * - Fetch all friends (secured with JWT)
 * - Fetch all pending and accepted friendships
 * 
 * Base URL: /friends
 */
@RestController
@RequestMapping("/friends")
public class FriendsController {

    /**
     * Service layer for handling business logic of Friends
     */
    @Autowired
    private FriendsService service;

    /**
     * Get all pending friend requests for a specific user
     * 
     * @param userId ID of the user
     * @return List of pending friend requests
     */
    @GetMapping("/pending/{userId}")
    public List<FriendsDTO> getPending(@PathVariable Integer userId) {
        return service.getPendingRequests(userId);
    }

    /**
     * Get all accepted friends for a specific user
     * 
     * @param userId ID of the user
     * @return List of accepted friends
     */
    @GetMapping("/accepted/{userId}")
    public List<FriendsDTO> getAccepted(@PathVariable Integer userId) {
        return service.getAcceptedFriends(userId);
    }
    
    /**
     * Get all friends in the system (JWT Protected API)
     * 
     * This API requires a valid Authorization token.
     * 
     * @param header Authorization header (Bearer Token)
     * @return List of all friends
     * @throws UnauthorizedException if token is missing or invalid
     */
    @GetMapping("/all")
    public List<FriendsDTO> getAllFriends(@RequestHeader("Authorization") String header) {

        // Check if token is present and valid format
        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException("Token missing ❌");
        }

        // Extract token
        String token = header.substring(7);

        // Validate token
        JwtUtil.validateToken(token);

        return service.getAllFriends();
    }
    
    /**
     * Get all pending friend requests in the system
     * 
     * @return List of all pending friendships
     */
    @GetMapping("/pending-all")
    public List<FriendsDTO> getAllPending() {
        return service.getAllPending();
    }

    /**
     * Get all accepted friendships in the system
     * 
     * @return List of all accepted friendships
     */
    @GetMapping("/accepted-all")
    public List<FriendsDTO> getAllAccepted() {
        return service.getAllAccepted();
    }
    
    @GetMapping("/mutual")
    public List<FriendsDTO> getMutualFriends(
            @RequestParam Integer user1,
            @RequestParam Integer user2) {

        return service.getMutualFriends(user1, user2);
    }
}