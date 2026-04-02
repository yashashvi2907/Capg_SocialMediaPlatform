package com.capg.service.impl;

import com.capg.dto.LikesDTO;
import com.capg.entity.Likes;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.repository.LikesRepository;

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
class LikesServiceImplTest {

    @Mock
    private LikesRepository likeRepository;

    @InjectMocks
    private LikesServiceImpl likesService;

    // ✅ 1. Positive Test Case
    @Test
    void testGetLikesByPost_Success() {

        // Mock User
        User user = new User();
        user.setUserID(1);
        user.setUsername("Disha");

        // Mock Post
        Post post = new Post();
        post.setPostID(100);

        // Mock Likes
        Likes like = new Likes();
        like.setLikeID(10);
        like.setTimestamp(LocalDateTime.now());
        like.setPost(post);

        // IMPORTANT (setter missing in your entity)
        like.setUser(user);   // ⚠️ Make sure setter exists

        when(likeRepository.findByPostPostID(100))
                .thenReturn(List.of(like));

        List<LikesDTO> result = likesService.getLikesByPost(100);

        assertEquals(1, result.size());
        assertEquals(10, result.get(0).getLikeID());
        assertEquals(1, result.get(0).getUserID());
        assertEquals("Disha", result.get(0).getUsername());
        assertEquals(100, result.get(0).getPostID());
    }

    // ❌ 2. Negative Test Case (No Likes Found)
    @Test
    void testGetLikesByPost_EmptyList() {

        when(likeRepository.findByPostPostID(200))
                .thenReturn(List.of());

        List<LikesDTO> result = likesService.getLikesByPost(200);

        assertTrue(result.isEmpty());
    }
}