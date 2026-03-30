package com.capg.service;

import java.time.LocalDateTime;

import java.util.List;

import com.capg.dto.PostDto;

public interface PostService {

    List<PostDto> getPostsByUser(Integer userId);

    List<PostDto> getPostsByDateRange(LocalDateTime start, LocalDateTime end);

    List<PostDto> searchPosts(String keyword);

    List<PostDto> getTrendingPosts();

    List<PostDto> getFeed(Integer userId);
}