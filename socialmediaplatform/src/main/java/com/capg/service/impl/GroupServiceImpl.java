package com.capg.service.impl;

import java.util.List;

import com.capg.exception.GroupNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capg.dto.GroupDTO;
import com.capg.entity.Group;
import com.capg.entity.User;
import com.capg.repository.GroupRepository;
import com.capg.service.GroupService;

/**
 * Implementation of GroupService interface.
 * Handles business logic related to Groups.
 */
@Service
public class GroupServiceImpl implements GroupService {

    /** Repository for accessing Group data. */
    @Autowired
    private GroupRepository groupRepository;

    /**
     * Retrieves all groups from the database.
     *
     * @return list of GroupDTO
     */
    @Override
    public List<GroupDTO> getAllGroups() {

        return groupRepository.findAll().stream().map(group -> {

            User admin = group.getAdmin();

            return new GroupDTO(
                    group.getGroupID(),
                    group.getGroupName(),
                    admin != null ? admin.getUserID() : null,
                    admin != null ? admin.getUsername() : null,
                    admin != null ? admin.getEmail() : null
            );

        }).toList();
    }

    /**
     * Retrieves a group by its ID.
     *
     * @param id group ID
     * @return GroupDTO
     * @throws GroupNotFoundException if group not found
     */
    @Override
    public GroupDTO getGroupById(Integer id) {

        Group group = groupRepository.findById(id)
                .orElseThrow(() -> new GroupNotFoundException("Group not found with id: " + id));

        User admin = group.getAdmin();

        return new GroupDTO(
                group.getGroupID(),
                group.getGroupName(),
                admin != null ? admin.getUserID() : null,
                admin != null ? admin.getUsername() : null,
                admin != null ? admin.getEmail() : null
        );
    }
}