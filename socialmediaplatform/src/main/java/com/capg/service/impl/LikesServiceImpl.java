//package com.capg.service.impl;
//
//import com.capg.dto.LikesDTO;
//import com.capg.entity.Likes;
//import com.capg.repository.LikesRepository;
//import com.capg.service.LikesService;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Service
//public class LikesServiceImpl implements LikesService {
//
//    @Autowired
//    private LikesRepository likeRepository;
//
//    private LikesDTO mapToDTO(Likes like) {
//        LikesDTO dto = new LikesDTO();
//
//        dto.setLikeID(like.getLikeID());
//        dto.setTimestamp(like.getTimestamp());
//
//        dto.setUserID(like.getUser().getUserID());
//        dto.setUsername(like.getUser().getUsername());
//
//        dto.setPostID(like.getPost().getPostID());
//
//        return dto;
//    }
//
//    @Override
//    public List<LikesDTO> getLikesByPost(Integer postId) {
//        return likeRepository.findByPostPostID(postId)
//                .stream()
//                .map(this::mapToDTO)
//                .collect(Collectors.toList());
//    }
//}
package com.capg.service.impl;

import com.capg.dto.LikesDTO;
import com.capg.entity.Likes;
import com.capg.repository.LikesRepository;
import com.capg.service.LikesService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikesServiceImpl implements LikesService {

    @Autowired
    private LikesRepository likeRepository;

    @Override
    public List<LikesDTO> getLikesByPost(Integer postId) {

        List<Likes> likes = likeRepository.findByPostPostID(postId);

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