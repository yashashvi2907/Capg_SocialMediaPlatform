//package com.capg.service.impl;
//
//import com.capg.dto.FriendsDTO;
//import com.capg.entity.Friends;
//import com.capg.entity.User;
//import com.capg.repository.IFriendsRepo;
//import com.capg.repository.UserRepository;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.*;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class FriendsServiceImplTest {
//
//    @Mock
//    private IFriendsRepo repo;
//
//    @Mock
//    private UserRepository userRepo;
//
//    @InjectMocks
//    private FriendsServiceImpl service;
//
//    private User user1;
//    private User user2;
//    private Friends friend;
//
//    @BeforeEach
//    void setup() {
//        user1 = new User();
//        user1.setUserID(1);
//
//        user2 = new User();
//        user2.setUserID(2);
//
//        friend = new Friends();
//        friend.setFriendshipID(100);
//        friend.setUser1(user1);
//        friend.setUser2(user2);
//        friend.setStatus("accepted");
//    }
//
//    // ==============================
//    // ✅ POSITIVE TEST CASE
//    // ==============================
//    
//    @Test
//    void testGetFriendById_Positive() {
//
//        when(repo.findById(100)).thenReturn(Optional.of(friend));
//
//        FriendsDTO result = service.getFriendById(100);
//
//        assertNotNull(result);
//        assertEquals(100, result.getFriendshipID());
//        assertEquals(1, result.getUserID1());
//        assertEquals(2, result.getUserID2());
//        assertEquals("accepted", result.getStatus());
//    }
//
//    // ==============================
//    // ❌ NEGATIVE TEST CASE
//    // ==============================
//    
//    
//    @Test
//    void testGetFriendById_Negative() {
//
//        when(repo.findById(999)).thenReturn(Optional.empty());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            service.getFriendById(999);
//        });
//
//        assertEquals("Friend not found", exception.getMessage());
//    }
//}