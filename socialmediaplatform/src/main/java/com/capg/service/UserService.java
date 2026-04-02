package com.capg.service;
import com.capg.dto.PostDTO;

import java.util.List;

public interface UserService {

    List<PostDTO> getUserPosts(Integer userId);

}