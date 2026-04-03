package com.capg.service;

import java.util.List;
import com.capg.dto.GroupDTO;

/**
 * Service interface for handling Group-related operations.
 */
public interface GroupService {

    /**
     * Retrieves all groups.
     *
     * @return list of GroupDTO
     */
    List<GroupDTO> getAllGroups();

    /**
     * Retrieves a group by its ID.
     *
     * @param id group ID
     * @return GroupDTO
     */
    GroupDTO getGroupById(Integer id);

    // Future methods (currently not implemented)
    // GroupDTO createGroup(GroupDTO dto);
    // GroupDTO updateGroup(Integer id, GroupDTO dto);
}