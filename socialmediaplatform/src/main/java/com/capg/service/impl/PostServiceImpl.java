package com.capg.service.impl;

import com.capg.dto.PostDTO;
import com.capg.entity.Friends;
import com.capg.entity.Post;
import com.capg.entity.User;
import com.capg.exception.BadRequestException;
import com.capg.exception.PostNotFoundException;
import com.capg.repository.IFriendsRepository;
import com.capg.repository.PostRepository;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service implementation for managing post-related operations.
 * <p>
 * Handles business logic such as fetching posts, filtering,
 * searching, trending logic, and user feed generation.
 * </p>
 */
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Autowired
    private IFriendsRepository friendsRepo;



    /**
     * Constructor-based dependency injection.
     *
     * @param postRepository repository for post data
     * @param friendsRepo    repository for friends relationship
     */
    public PostServiceImpl(PostRepository postRepository, IFriendsRepository friendsRepo) {
        this.postRepository = postRepository;
        this.friendsRepo = friendsRepo;
    }

    /**
     * Converts Post entity to PostDto.
     *
     * @param post the Post entity
     * @return mapped PostDto object
     */
    private PostDTO mapToDTO(Post post) {
        final PostDTO dto = new PostDTO();

        dto.setPostID(post.getPostID());
        dto.setContent(post.getContent());
        dto.setTimestamp(post.getTimestamp());

        dto.setUserID(post.getUser().getUserID());
        dto.setUsername(post.getUser().getUsername());

        dto.setLikeCount(post.getLikes().size());
        dto.setCommentCount(post.getComments().size());

        return dto;
    }

    /**
     * Fetches posts created by a specific user.
     *
     * @param userId the user ID
     * @return list of PostDto
     * @throws BadRequestException    if userId is null
     * @throws PostNotFoundException  if no posts are found
     */
    @Override
    public List<PostDTO> getPostsByUser(Integer userId) {

        if (userId == null) {
            throw new BadRequestException("User ID cannot be null");
        }

        final List<Post> posts = postRepository.findByUserUserID(userId);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found for user: " + userId);
        }

        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    /**
     * Fetches posts within a specified date range.
     *
     * @param start start date-time
     * @param end   end date-time
     * @return list of PostDto
     * @throws BadRequestException    if inputs are null
     * @throws PostNotFoundException  if no posts exist in range
     */
    @Override
    public List<PostDTO> getPostsByDateRange(LocalDateTime start, LocalDateTime end) {

        if (start == null || end == null) {
            throw new BadRequestException("Date range cannot be null");
        }

        final List<Post> posts = postRepository.findByTimestampBetween(start, end);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found in given date range");
        }

        return posts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    /**
     * Searches posts based on a keyword.
     *
     * @param keyword the search keyword
     * @return list of PostDto
     * @throws BadRequestException    if keyword is null/blank
     * @throws PostNotFoundException  if no matching posts found
     */
    @Override
    public List<PostDTO> searchPosts(String keyword) {

        if (keyword == null || keyword.isBlank()) {
            throw new BadRequestException("Keyword cannot be empty");
        }

        final List<PostDTO> posts = postRepository.searchPostsByKeyword(keyword);

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts found with keyword: " + keyword);
        }

        return posts;
    }

    /**
     * Retrieves top trending posts.
     * <p>
     * Currently sorts posts by latest timestamp and limits to top 10.
     * </p>
     *
     * @return list of trending PostDto
     * @throws PostNotFoundException if no posts exist
     */
    @Override
    public List<PostDTO> getTrendingPosts() {

        final List<Post> posts = postRepository.findAll();

        if (posts.isEmpty()) {
            throw new PostNotFoundException("No posts available");
        }

        return posts.stream()
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .limit(10)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    /**
     * Generates a personalized feed for a user.
     * <p>
     * Includes posts from:
     * <ul>
     *     <li>The user</li>
     *     <li>The user's friends</li>
     * </ul>
     * Sorted by latest posts.
     * </p>
     *
     * @param userId the user ID
     * @return list of PostDto representing the feed
     * @throws BadRequestException    if userId is null
     * @throws PostNotFoundException  if no feed posts found
     */
    @Override
    public List<PostDTO> getFeed(Integer userId) {

        if (userId == null) {
            throw new BadRequestException("User ID cannot be null");
        }

        final User user = new User();
        user.setUserID(userId);

        final List<Friends> friends = friendsRepo.findByUser1OrUser2(user, user);

        final Set<Integer> friendIds = new HashSet<>();

        for (final Friends f : friends) {
            if (f.getUser1().getUserID().equals(userId)) {
                friendIds.add(f.getUser2().getUserID());
            } else {
                friendIds.add(f.getUser1().getUserID());
            }
        }

        friendIds.add(userId);

        final List<Post> posts = postRepository.findAll();

        final List<Post> feedPosts = posts.stream()
                .filter(p -> friendIds.contains(p.getUser().getUserID()))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .collect(Collectors.toList());

        if (feedPosts.isEmpty()) {
            throw new PostNotFoundException("No feed posts available");
        }

        return feedPosts.stream().map(this::mapToDTO).collect(Collectors.toList());
    }
}