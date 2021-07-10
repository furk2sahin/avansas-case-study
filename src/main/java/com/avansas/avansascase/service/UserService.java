package com.avansas.avansascase.service;

import com.avansas.avansascase.dto.UserDto;

import java.util.List;

public interface UserService {
    UserDto addNewUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto findUserById(Long id);
    UserDto updateUser(UserDto userDto);
    void deleteUserById(Long id);
}
