package com.capg.service.impl;

import com.capg.dto.FriendsDTO;
import com.capg.entity.Friends;
import com.capg.entity.User;
import com.capg.exception.NoDataFoundException;
import com.capg.repository.IFriendsRepository;
import com.capg.repository.UserRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Test class for FriendsServiceImpl.
 *
 * <p>This class contains unit test cases for testing
 * the functionality of fetching pending friend requests.</p>
 *
 * <p>It uses Mockito for mocking dependencies and JUnit 5 for testing.</p>
 *
 * @author Mansi
 */
@ExtendWith(MockitoExtension.class)
public class FriendsServiceImplTest {

    /**
     * Mock repository for Friends entity.
     */
    @Mock
    private IFriendsRepository repo;

    /**
     * Mock repository for User entity.
     */
    @Mock
    private UserRepository userRepo;

    /**
     * Injects mocked dependencies into service.
     */
    @InjectMocks
    private FriendsServiceImpl service;

    /**
     * Test user object used in test cases.
     */
    private User user;

    /**
     * Initializes test data before each test execution.
     */
    @BeforeEach
    void setUp() {
        user = new User();
        user.setUserID(1);
    }

    /**
     * ✅ Positive Test Case:
     * 
     * <p>Tests fetching pending friend requests when data exists.</p>
     *
     * <p>Expected:
     * <ul>
     *     <li>Non-null result</li>
     *     <li>List size = 1</li>
     *     <li>Status = "pending"</li>
     * </ul>
     * </p>
     */
    @Test
    void testGetPendingRequests_Positive() {

        Friends f = new Friends();
        f.setFriendshipID(101);
        f.setUser1(user);
        f.setUser2(user);
        f.setStatus("pending");

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(repo.findByUser2AndStatus(user, "pending")).thenReturn(List.of(f));

        List<FriendsDTO> result = service.getPendingRequests(1);

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("pending", result.get(0).getStatus());
    }

    /**
     * ❌ Negative Test Case:
     * 
     * <p>Tests fetching pending requests when no data is available.</p>
     *
     * <p>Expected:
     * <ul>
     *     <li>Throws NoDataFoundException</li>
     * </ul>
     * </p>
     */
    @Test
    void testGetPendingRequests_NoData() {

        when(userRepo.findById(1)).thenReturn(Optional.of(user));
        when(repo.findByUser2AndStatus(user, "pending")).thenReturn(List.of());

        assertThrows(NoDataFoundException.class, () -> {
            service.getPendingRequests(1);
        });
    }
}