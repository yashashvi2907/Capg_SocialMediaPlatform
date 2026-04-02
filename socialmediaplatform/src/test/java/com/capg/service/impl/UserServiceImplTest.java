package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.repository.PostRepository;
import com.capg.exception.UserNotFound;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @InjectMocks
    private UserServiceImpl userService;

    // ✅ Positive Test
    @Test
    void testGetUserPosts_Success() {

        User user = new User();
        user.setUserID(1);
        user.setUsername("testUser");

        Post post = new Post();
        post.setPostID(10);
        post.setContent("Hello");
        post.setTimestamp(LocalDateTime.now());
        post.setUser(user);
        post.setLikes(List.of());
        post.setComments(List.of());

        when(postRepository.findByUserUserID(1))
                .thenReturn(List.of(post));

        List<PostDto> result = userService.getUserPosts(1);

        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getContent());
        assertEquals(1, result.get(0).getUserID());
    }

    // ❌ Negative Test
    @Test
    void testGetUserPosts_NoPosts() {

        when(postRepository.findByUserUserID(1))
                .thenReturn(List.of());

        UserNotFound ex = assertThrows(
                UserNotFound.class,
                () -> userService.getUserPosts(1)
        );

        assertEquals("No posts found for user with id: 1", ex.getMessage());
    }
}