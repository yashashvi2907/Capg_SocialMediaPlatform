package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.exception.UserNotFound;
import com.capg.repository.PostRepository;
import com.capg.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service implementation for handling user-related operations.
 * Provides business logic for retrieving user posts.
 */
@Service
public class UserServiceImpl implements UserService {

    private final PostRepository postRepository;

    public UserServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public List<PostDto> getUserPosts(final Integer userId) {

        final List<Post> posts = postRepository.findByUserUserID(userId);

        if (posts.isEmpty()) {
            throw new UserNotFound("No posts found for user with id: " + userId);
        }

        return posts.stream().map(post -> {
            final PostDto dto = new PostDto();

            dto.setPostID(post.getPostID());
            dto.setContent(post.getContent());
            dto.setTimestamp(post.getTimestamp());

            dto.setUserID(post.getUser().getUserID());
            dto.setUsername(post.getUser().getUsername());

            dto.setLikeCount(post.getLikes().size());
            dto.setCommentCount(post.getComments().size());

            return dto;
        }).toList();
    }
}