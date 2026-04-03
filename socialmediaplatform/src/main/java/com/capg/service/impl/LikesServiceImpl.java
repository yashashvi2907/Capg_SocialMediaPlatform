package com.capg.service.impl;

import com.capg.dto.LikesDTO;
import com.capg.entity.Likes;
import com.capg.exception.LikesNotFoundException;
import com.capg.repository.LikesRepository;
import com.capg.service.LikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of LikesService interface.
 * Handles business logic related to Likes.
 */
@Service
public class LikesServiceImpl implements LikesService {

    /** Repository for accessing Likes data. */
    @Autowired
    private LikesRepository likeRepository;

    /**
     * Retrieves all likes for a given post ID.
     *
     * @param postId ID of the post
     * @return list of LikesDTO
     * @throws LikesNotFoundException if no likes found
     */
    @Override
    public List<LikesDTO> getLikesByPost(Integer postId) {

        List<Likes> likes = likeRepository.findByPostPostID(postId);

        if (likes.isEmpty()) {
            throw new LikesNotFoundException("No likes found for post id: " + postId);
        }

        return likes.stream().map(like -> {
            LikesDTO dto = new LikesDTO();

            dto.setLikeID(like.getLikeID());
            dto.setTimestamp(like.getTimestamp());

            if (like.getUser() != null) {
                dto.setUserID(like.getUser().getUserID());
                dto.setUsername(like.getUser().getUsername());
            }

            if (like.getPost() != null) {
                dto.setPostID(like.getPost().getPostID());
            }

            return dto;

        }).toList();
    }
}