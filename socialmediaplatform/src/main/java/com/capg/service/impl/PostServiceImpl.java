package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Friends;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.exception.BadRequestException;
import com.capg.exception.PostNotFoundException;
import com.capg.repository.IFriendsRepo;
import com.capg.repository.PostRepository;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private IFriendsRepo friendsRepo;

    // ✅ DTO Mapper
    private PostDto mapToDTO(Post post) {
        PostDto dto = new PostDto();

        dto.setPostID(post.getPostID());
        dto.setContent(post.getContent());
        dto.setTimestamp(post.getTimestamp());

        dto.setUserID(post.getUser().getUserID());
        dto.setUsername(post.getUser().getUsername());

        dto.setLikeCount(post.getLikes().size());
        dto.setCommentCount(post.getComments().size());

        return dto;
    }

    //Posts by User
    @Override
    public List<PostDto> getPostsByUser(Integer userId) {

        if (userId == null) {
            throw new BadRequestException("User ID cannot be null");
        }

        List<Post> posts = postRepository.findByUserUserID(userId);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found for user: " + userId);
        }

        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //Posts by Date Range
    @Override
    public List<PostDto> getPostsByDateRange(LocalDateTime start, LocalDateTime end) {

        if (start == null || end == null) {
            throw new BadRequestException("Date range cannot be null");
        }

        List<Post> posts = postRepository.findByTimestampBetween(start, end);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found in given date range");
        }

        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    // Search by Keyword
    @Override
    public List<PostDto> searchPosts(String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            throw new BadRequestException("Keyword cannot be empty");
        }

        List<Post> posts = postRepository.findByContentContainingIgnoreCase(keyword);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found with keyword: " + keyword);
        }

        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    //Trending (Simple - Latest Posts)
    @Override
    public List<PostDto> getTrendingPosts() {

        List<Post> posts = postRepository.findAll();

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts available");
        }

        return posts.stream()
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .limit(10)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    //Feed (Friends + Self)
    @Override
    public List<PostDto> getFeed(Integer userId) {

        if (userId == null) {
            throw new BadRequestException("User ID cannot be null");
        }

        User user = new User();
        user.setUserID(userId);

        List<Friends> friends = friendsRepo.findByUser1OrUser2(user, user);

        Set<Integer> friendIds = new HashSet<>();

        for (Friends f : friends) {
            if (f.getUser1().getUserID().equals(userId)) {
                friendIds.add(f.getUser2().getUserID());
            } else {
                friendIds.add(f.getUser1().getUserID());
            }
        }

        friendIds.add(userId);

        List<Post> posts = postRepository.findAll();

        List<Post> feedPosts = posts.stream()
                .filter(p -> friendIds.contains(p.getUser().getUserID()))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .collect(Collectors.toList());

        if (feedPosts.isEmpty()) {
            throw new PostNotFoundException("No feed posts available");
        }

        return feedPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}