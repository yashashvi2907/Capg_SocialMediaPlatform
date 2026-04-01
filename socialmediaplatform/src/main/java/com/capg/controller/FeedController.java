package com.capg.controller;

import com.capg.dto.PostDto;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/feed")
public class FeedController {

    @Autowired
    private PostService postService;

    //Get Feed for a User (Friends + Self)
    @GetMapping("/{userId}")
    public ResponseEntity<List<PostDto>> getFeed(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.getFeed(userId));
    }
}