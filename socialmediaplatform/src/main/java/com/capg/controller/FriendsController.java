package com.capg.controller;

import com.capg.dto.FriendsDTO;
import com.capg.service.FriendsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsService service;

//    @PostMapping("/add")
//    public FriendsDTO addFriend(@RequestBody FriendsDTO dto) {
//        return service.addFriend(dto);
//    }

    @GetMapping("/all")
    public List<FriendsDTO> getAll() {
        return service.getAllFriends();
    }

    @GetMapping("/pending/{userId}")
    public List<FriendsDTO> getPending(@PathVariable Integer userId) {
        return service.getPendingRequests(userId);
    }

    @GetMapping("/accepted/{userId}")
    public List<FriendsDTO> getAccepted(@PathVariable Integer userId) {
        return service.getAcceptedFriends(userId);
    }
}