package com.capg.service;

import java.util.List;
import com.capg.dto.GroupDTO;

public interface GroupService {

    List<GroupDTO> getAllGroups();

    GroupDTO getGroupById(Integer id);
}