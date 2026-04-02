package com.capg.service.impl;

import com.capg.dto.LikeDto;
import com.capg.entity.Likes;
import com.capg.repository.LikeRepository;
import com.capg.service.LikeService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LikeServiceImpl implements LikeService {

    @Autowired
    private LikeRepository likeRepository;

    private LikeDto mapToDTO(Likes like) {
        LikeDto dto = new LikeDto();

        dto.setLikeID(like.getLikeID());
        dto.setTimestamp(like.getTimestamp());

        dto.setUserID(like.getUser().getUserID());
        dto.setUsername(like.getUser().getUsername());

        dto.setPostID(like.getPost().getPostID());

        return dto;
    }

    @Override
    public List<LikeDto> getLikesByPost(Integer postId) {
        return likeRepository.findByPostPostID(postId)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }
}