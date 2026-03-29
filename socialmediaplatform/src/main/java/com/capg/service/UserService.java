package com.capg.service;

import com.capg.dto.UserDto;

import java.util.List;

public interface UserService {

    // first two are default method of jpa
    UserDto getUserById(Integer userId);
    List<UserDto> getAllUsers();

    // custom api
    UserDto getUserByUsername(String username);
    UserDto getUserByEmail(String email);
}