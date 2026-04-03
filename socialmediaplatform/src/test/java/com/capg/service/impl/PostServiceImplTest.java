package com.capg.service.impl;

import com.capg.dto.PostDTO;
import com.capg.exception.BadRequestException;
import com.capg.exception.PostNotFoundException;
import com.capg.repository.IFriendsRepository;
import com.capg.repository.PostRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Unit tests for PostServiceImpl (searchPosts method).
 * Uses DTO-based repository approach.
 */
@ExtendWith(MockitoExtension.class)
class PostServiceImplTest {

    /** Valid keyword for positive test */
    private static final String VALID_KEYWORD = "java";

    /** Invalid keyword (blank input) */
    private static final String EMPTY_KEYWORD = " ";

    /** Sample user ID */
    private static final Integer USER_ID = 1;

    /** Sample content */
    private static final String CONTENT = "Learning Java Streams";

    /** Mocked PostRepository */
    @Mock
    private PostRepository postRepository;

    /** Mocked FriendsRepo (required dependency) */
    @Mock
    private IFriendsRepository friendsRepo;

    /** Service under test */
    @InjectMocks
    private PostServiceImpl postService;

    public PostServiceImplTest() {
    }

    /**
     * Positive Test Case
     */
    @Test
    void testSearchPostsValidKeywordReturnsPostList() {

        final PostDTO dto = new PostDTO(
                100,
                CONTENT,
                LocalDateTime.now(),
                USER_ID,
                "test_user",
                0,
                0
        );

        final List<PostDTO> mockPosts = List.of(dto);

        when(postRepository.searchPostsByKeyword(VALID_KEYWORD))
                .thenReturn(mockPosts);

        final List<PostDTO> result = postService.searchPosts(VALID_KEYWORD);

        assertNotNull(result,"Result list should not be null");
        assertEquals(1, result.size(),"Result list should contain exactly one element");
        assertEquals(CONTENT, result.getFirst().getContent(),"Posts content should match expected message");

        verify(postRepository, times(1))
                .searchPostsByKeyword(VALID_KEYWORD);
    }

    /**
     * Negative Test Case: No posts found
     */
    @Test
    void testSearchPostsNoPostsFoundThrowsException() {

        when(postRepository.searchPostsByKeyword(VALID_KEYWORD))
                .thenReturn(Collections.emptyList());

        final PostNotFoundException exception = assertThrows(
                PostNotFoundException.class,
                () -> postService.searchPosts(VALID_KEYWORD)
        );

        assertEquals("No posts found with keyword: " + VALID_KEYWORD,
                exception.getMessage(),"Exception message should match expected text");

        verify(postRepository, times(1))
                .searchPostsByKeyword(VALID_KEYWORD);
    }

    /**
     * Negative Test Case: Invalid keyword
     */
    @Test
    void testSearchPostsInvalidKeywordThrowsException() {

        final BadRequestException exception = assertThrows(
                BadRequestException.class,
                () -> postService.searchPosts(EMPTY_KEYWORD)
        );

        assertEquals("Keyword cannot be empty", exception.getMessage(),"Exception message should match expected text");

        verify(postRepository, never())
                .searchPostsByKeyword(anyString());
    }
}