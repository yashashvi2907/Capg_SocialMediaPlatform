package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.repository.PostRepository;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;
    //Common DTO mapper
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

        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .filter(p -> p.getUser().getUserID().equals(userId))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}