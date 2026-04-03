package com.capg.service.impl;

import com.capg.dto.PostDTO;
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

/**
 * Unit test class for UserServiceImpl.
 * Verifies behavior of retrieving user posts using mocked repository.
 */
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    /**
     * Mocked PostRepository dependency.
     */
    @Mock
    private PostRepository postRepository;

    /**
     * UserServiceImpl instance with injected mocks.
     */
    @InjectMocks
    private UserServiceImpl userService;

    /**
     * Tests successful retrieval of posts for a user.
     */
    @Test
    void testGetUserPosts_Success() {

        final User user = new User();
        user.setUserID(1);
        user.setUsername("testUser");

        final Post post = new Post();
        post.setPostID(10);
        post.setContent("Hello");
        post.setTimestamp(LocalDateTime.now());
        post.setUser(user);
        post.setLikes(List.of());
        post.setComments(List.of());

        when(postRepository.findByUserUserID(1))
                .thenReturn(List.of(post));

        final List<PostDTO> result = userService.getUserPosts(1);

        assertEquals(1, result.size());
        assertEquals("Hello", result.get(0).getContent());
        assertEquals(1, result.get(0).getUserID());
    }

    /**
     * Tests scenario where no posts exist for a user.
     * Expects UserNotFound exception to be thrown.
     */
    @Test
    void testGetUserPosts_NoPosts() {

        when(postRepository.findByUserUserID(1))
                .thenReturn(List.of());

        final UserNotFound ex = assertThrows(
                UserNotFound.class,
                () -> userService.getUserPosts(1)
        );

        assertEquals("No posts found for user with id: 1", ex.getMessage());
    }
}