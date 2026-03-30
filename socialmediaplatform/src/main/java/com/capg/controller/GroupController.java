package com.capg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.capg.dto.GroupDTO;
import com.capg.service.GroupService;

@RestController
@RequestMapping("/api/groups")
public class GroupController {

    @Autowired
    private GroupService groupService;

    // Get all groups
    @GetMapping
    public List<GroupDTO> getAllGroups() {
        return groupService.getAllGroups();
    }

    // Get group by ID
    @GetMapping("/{id}")
    public GroupDTO getGroupById(@PathVariable Integer id) {
        return groupService.getGroupById(id);
    }
}