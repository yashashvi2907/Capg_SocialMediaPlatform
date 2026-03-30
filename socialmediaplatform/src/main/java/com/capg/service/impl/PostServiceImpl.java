package com.capg.service.impl;

import com.capg.dto.PostDto;
import com.capg.entity.Friends;
import com.capg.entity.Post;
import com.capg.entity.User;

import com.capg.repository.IFriendsRepo;
import com.capg.repository.PostRepository;
import com.capg.service.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

	@Autowired
	private PostRepository postRepository;

	// Use the new IFriendsRepo instead of old FriendsRepository
	@Autowired
	private IFriendsRepo friendsRepo;

	// DTO mapper (no change needed here)
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

    // inject the repo

    @Override
    public List<PostDto> getFeed(Integer userId) {

        User user = new User();
        user.setUserID(userId);

        // Use the instance, not the interface name
        List<Friends> friends = friendsRepo.findByUser1OrUser2(user, user);

        Set<Integer> friendIds = new HashSet<>();
        for (Friends f : friends) {
            if (f.getUser1().getUserID().equals(userId)) {
                friendIds.add(f.getUser2().getUserID());
            } else {
                friendIds.add(f.getUser1().getUserID());
            }
        }

        friendIds.add(userId);

        List<Post> posts = postRepository.findAll();

        return posts.stream()
                .filter(p -> friendIds.contains(p.getUser().getUserID()))
                .sorted((a, b) -> b.getTimestamp().compareTo(a.getTimestamp()))
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}