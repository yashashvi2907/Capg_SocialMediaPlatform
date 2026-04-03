package com.capg.service;

import java.util.List;
import com.capg.dto.LikesDTO;

/**
 * Service interface for handling Likes-related operations.
 */
public interface LikesService {

    /**
     * Retrieves all likes for a given post.
     *
     * @param postId ID of the post
     * @return list of LikesDTO
     */
    List<LikesDTO> getLikesByPost(Integer postId);
}