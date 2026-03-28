package com.capg.controller;

import com.capg.dto.PostDto;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    @Autowired
    private PostService postService;

    // Get Trending Posts
    @GetMapping("/trending")
    public List<PostDto> getTrendingPosts() {
        return postService.getTrendingPosts();
    }
}