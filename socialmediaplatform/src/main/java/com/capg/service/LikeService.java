package com.capg.service;


import java.util.List;

import com.capg.dto.LikeDto;

public interface LikeService {

    List<LikeDto> getLikesByPost(Integer postId);

}