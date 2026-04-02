package com.capg.service.impl;

import com.capg.dto.GroupDTO;
import com.capg.entity.Group;
import com.capg.entity.User;
import com.capg.repository.GroupRepository;
import com.capg.exception.GroupNotFoundException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class GroupServiceImplTest {

    @Mock
    private GroupRepository groupRepository;

    @InjectMocks
    private GroupServiceImpl groupService;

    // ✅ 1. Positive Test (getGroupById)
    @Test
    void testGetGroupById_Success() {

        User admin = new User();
        admin.setUserID(1);
        admin.setUsername("admin");
        admin.setEmail("admin@test.com");

        Group group = new Group();
        group.setGroupID(10);
        group.setGroupName("Test Group");
        group.setAdmin(admin);

        when(groupRepository.findById(10))
                .thenReturn(Optional.of(group));

        GroupDTO result = groupService.getGroupById(10);

        assertEquals(10, result.getGroupID());
        assertEquals("Test Group", result.getGroupName());
        assertEquals(1, result.getAdminID());
        assertEquals("admin", result.getAdminUsername());
    }

    // Negative Test (Exception case)
    @Test
    void testGetGroupById_NotFound() {

        when(groupRepository.findById(10))
                .thenReturn(Optional.empty());

        GroupNotFoundException ex = assertThrows(
                GroupNotFoundException.class,
                () -> groupService.getGroupById(10)
        );

        assertEquals("Group not found with id: 10", ex.getMessage());
    }

    // ✅ 3. Extra (getAllGroups - optional but good)
    @Test
    void testGetAllGroups() {

        Group group = new Group();
        group.setGroupID(1);
        group.setGroupName("Group A");

        when(groupRepository.findAll())
                .thenReturn(List.of(group));

        List<GroupDTO> result = groupService.getAllGroups();

        assertEquals(1, result.size());
        assertEquals("Group A", result.get(0).getGroupName());
    }
}