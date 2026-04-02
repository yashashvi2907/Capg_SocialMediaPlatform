package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.exception.BadRequestException;
import com.capg.exception.PostNotFoundException;
import com.capg.repository.IFriendsRepo;
import com.capg.repository.PostRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private IFriendsRepo friendsRepo;

    @InjectMocks
    private PostServiceImpl postService;

    //Positive Test Case
    @Test
    void testSearchPosts_Success() {

        String keyword = "java";

        // Mock User
        User user = new User();
        user.setUserID(1);
        user.setUsername("john");

        // Mock Post
        Post post = new Post();
        post.setPostID(101);
        post.setContent("Learning Java Streams");
        post.setTimestamp(LocalDateTime.now());
        post.setUser(user);
        post.setLikes(new ArrayList<>());
        post.setComments(new ArrayList<>());

        List<Post> mockPosts = Arrays.asList(post);

        when(postRepository.findByContentContainingIgnoreCase(keyword))
                .thenReturn(mockPosts);

        List<PostDto> result = postService.searchPosts(keyword);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("Learning Java Streams", result.get(0).getContent());
        assertEquals("john", result.get(0).getUsername());

        verify(postRepository, times(1))
                .findByContentContainingIgnoreCase(keyword);
    }

    //Negative Test Case (No Posts Found)
    @Test
    void testSearchPosts_NoResults() {

        String keyword = "python";

        when(postRepository.findByContentContainingIgnoreCase(keyword))
                .thenReturn(Collections.emptyList());

        PostNotFoundException exception = assertThrows(
                PostNotFoundException.class,
                () -> postService.searchPosts(keyword)
        );

        assertEquals("No posts found with keyword: python", exception.getMessage());

        verify(postRepository, times(1))
                .findByContentContainingIgnoreCase(keyword);
    }

    //Negative Test Case (Invalid Keyword)
    @Test
    void testSearchPosts_InvalidKeyword() {

        String keyword = "   ";

        BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> postService.searchPosts(keyword)
        );

        assertEquals("Keyword cannot be empty", exception.getMessage());

        verify(postRepository, never())
                .findByContentContainingIgnoreCase(anyString());
    }
}