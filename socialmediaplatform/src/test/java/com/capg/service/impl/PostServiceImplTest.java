package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.entity.Friends;
import com.capg.exception.PostNotFoundException;
import com.capg.repository.PostRepository;
import com.capg.repository.IFriendsRepo;
import com.capg.service.impl.PostServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.LocalDateTime;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PostServiceImplTest {

    @Mock
    private PostRepository postRepository;

    @Mock
    private IFriendsRepo friendsRepo;

    @InjectMocks
    private PostServiceImpl postService;

    //Initialize mocks
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //TRENDING TESTS

    //Positive Test
    @Test
    void testGetTrendingPostsSuccess() {

        Post post = new Post();
        post.setPostID(1);
        post.setContent("coding tips");
        post.setTimestamp(LocalDateTime.now());

        User user = new User();
        user.setUserID(1);
        user.setUsername("user1");

        post.setUser(user);
        post.setComments(new ArrayList<>());
        post.setLikes(new ArrayList<>());

        when(postRepository.findAll()).thenReturn(List.of(post));

        List<PostDto> result = postService.getTrendingPosts();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals("coding tips", result.get(0).getContent());
    }

    // Negative Test
    @Test
    void testGetTrendingPostsNoData() {

        when(postRepository.findAll()).thenReturn(Collections.emptyList());

        assertThrows(PostNotFoundException.class, () -> {
            postService.getTrendingPosts();
        });
    }

    //FEED TESTS

    @Test
    void getFeedValidUserReturnsFeedPosts() {

        Integer userId = 1;

        // friend user
        User friendUser = new User();
        friendUser.setUserID(2);

        // friendship
        Friends friend = new Friends();
        User user = new User();
        user.setUserID(userId);

        friend.setUser1(user);
        friend.setUser2(friendUser);

        // post by friend
        Post post = new Post();
        post.setPostID(101);
        post.setContent("hello from friend");
        post.setTimestamp(LocalDateTime.now());
        post.setUser(friendUser);
        post.setComments(new ArrayList<>());
        post.setLikes(new ArrayList<>());

        when(friendsRepo.findByUser1OrUser2(any(User.class), any(User.class)))
                .thenReturn(List.of(friend));

        when(postRepository.findAll())
                .thenReturn(List.of(post));

        List<PostDto> result = postService.getFeed(userId);

        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    //Negative Test
    @Test
    void testGetFeedNoPosts() {

        Integer userId = 1;

        User user = new User();
        user.setUserID(userId);

        when(friendsRepo.findByUser1OrUser2(user, user))
                .thenReturn(Collections.emptyList());

        when(postRepository.findAll())
                .thenReturn(Collections.emptyList());

        assertThrows(PostNotFoundException.class, () -> {
            postService.getFeed(userId);
        });
    }
}
