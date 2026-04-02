package com.capg.controller;

import com.capg.dto.PostDto;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    //Get Trending Posts
    @GetMapping("/trending")
    public ResponseEntity<List<PostDto>> getTrendingPosts() {
        return ResponseEntity.ok(postService.getTrendingPosts());
    }

    //Get Posts by User
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        return ResponseEntity.ok(postService.getPostsByUser(userId));
    }

    //Get Posts by Date Range
    @GetMapping("/date")
    public ResponseEntity<List<PostDto>> getPostsByDateRange(
            @RequestParam LocalDateTime start,
            @RequestParam LocalDateTime end) {

        return ResponseEntity.ok(postService.getPostsByDateRange(start, end));
    }

    //Search Posts by specific Keyword
    @GetMapping("/search")
    public ResponseEntity<List<PostDto>> searchPosts(@RequestParam String keyword) {
        return ResponseEntity.ok(postService.searchPosts(keyword));
    }

}