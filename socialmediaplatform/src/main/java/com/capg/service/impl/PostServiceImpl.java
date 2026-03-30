package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Friends;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.repository.FriendsRepository;
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
    private FriendsRepository friendsRepository;

    // DTO mapper
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

    @Override
    public List<PostDto> getPostsByUser(Integer userId) {
        return postRepository.findByUserUserID(userId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getPostsByDateRange(LocalDateTime start, LocalDateTime end) {
        return postRepository.findByTimestampBetween(start, end)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> searchPosts(String keyword) {
        return postRepository.findByContentContainingIgnoreCase(keyword)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getTrendingPosts() {
        List<Post> posts = postRepository.findAll();
        return posts.stream()
                .sorted((p1, p2) ->
                        Integer.compare(p2.getLikes().size(), p1.getLikes().size()))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<PostDto> getFeed(Integer userId) {

        // Step 1: Create dummy user object (only ID needed)
        User user = new User();
        user.setUserID(userId);

        // Step 2: Get friends where user is user1
        List<Friends> friends1 = friendsRepository.findByUser1UserIDAndStatus(userId, "ACCEPTED");

        // Step 3: Get friends where user is user2
        List<Friends> friends2 = friendsRepository.findByUser2UserIDAndStatus(userId, "ACCEPTED");

        // Step 4: Collect all friend IDs
        Set<Integer> friendIds = new HashSet<>();

        for (Friends f : friends1) {
            friendIds.add(f.getUser2().getUserID());
        }

        for (Friends f : friends2) {
            friendIds.add(f.getUser1().getUserID());
        }

        // Step 5: Add self posts also (optional but recommended)
        friendIds.add(userId);

        // Step 6: Fetch all posts and filter
        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .filter(p -> friendIds.contains(p.getUser().getUserID()))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}