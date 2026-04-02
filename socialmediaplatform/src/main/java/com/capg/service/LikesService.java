package com.capg.service;


import java.util.List;

import com.capg.dto.LikesDTO;

public interface LikesService {

    List<LikesDTO> getLikesByPost(Integer postId);


}