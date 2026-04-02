package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.exception.UserNotFound;
import com.capg.repository.PostRepository;
import com.capg.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PostRepository postRepository;

    @Override
    public List<PostDto> getUserPosts(Integer userId) {

        List<Post> posts = postRepository.findByUserUserID(userId);

        if (posts.isEmpty()) {
            throw new UserNotFound("No posts found for user with id: " + userId);
        }

        return posts.stream().map(post -> {
            PostDto dto = new PostDto();

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