package com.capg.controller;

import com.capg.dto.PostDto;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class FeedController {

    @Autowired
    private PostService postService;

    //Get User Feed
    @GetMapping("/feed/{userId}")
    public List<PostDto> getUserFeed(@PathVariable Integer userId) {
        return postService.getFeed(userId);
    }
}
