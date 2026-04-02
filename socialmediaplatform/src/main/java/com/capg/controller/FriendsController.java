package com.capg.controller;

import com.capg.dto.FriendsDTO;
import com.capg.entity.Friends;
import com.capg.Exception.UnauthorizedException;
import com.capg.security.JwtUtil;
import com.capg.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsService service;

    @GetMapping("/pending/{userId}")
    public List<FriendsDTO> getPending(@PathVariable Integer userId) {
        return service.getPendingRequests(userId);
    }

    @GetMapping("/accepted/{userId}")
    public List<FriendsDTO> getAccepted(@PathVariable Integer userId) {
        return service.getAcceptedFriends(userId);
    }
    
    @GetMapping("/all")
    public List<FriendsDTO> getAllFriends(@RequestHeader("Authorization") String header) {

        if (header == null || !header.startsWith("Bearer ")) {
            throw new UnauthorizedException("Token missing ❌");
        }

        String token = header.substring(7);
        JwtUtil.validateToken(token);
        return service.getAllFriends();
    }
    
    // All pending requests
    @GetMapping("/pending-all")
    public List<FriendsDTO> getAllPending() {
        return service.getAllPending();
    }

    //  All accepted friends
    @GetMapping("/accepted-all")
    public List<FriendsDTO> getAllAccepted() {
        return service.getAllAccepted();
    }
}