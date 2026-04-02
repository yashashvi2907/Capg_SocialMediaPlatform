package com.capg.service;

import com.capg.dto.PostDto;

import java.util.List;

public interface UserService {

    List<PostDto> getUserPosts(Integer userId);

}