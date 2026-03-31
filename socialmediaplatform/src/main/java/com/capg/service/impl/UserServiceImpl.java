package com.capg.service.impl;

//import com.capg.dto.PostDto;
import com.capg.entity.Post;
import com.capg.entity.User;
//import com.capg.repository.PostRepository;
import com.capg.repository.UserRepository;
import com.capg.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    private PostRepository postRepository;

//    @Override
//    public List<PostDto> getUserPosts(Integer userId) {
//
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        List<Post> posts = postRepository.findByUser(user);

//        return posts.stream().map(post -> {
//            PostDto dto = new PostDto();
//            dto.setPostID(post.getPostID());
//            dto.setContent(post.getContent());
//            dto.setTimestamp(post.getTimestamp());
//            return dto;
//        }).toList();
//    }
}