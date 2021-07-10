package com.avansas.avansascase.service;

import com.avansas.avansascase.dto.UserDto;
import com.avansas.avansascase.model.User;

import java.util.List;

public interface UserService {
    User addNewUser(UserDto userDto);
    List<User> getAllUsers();
    User findUserById(Long id);
    User updateUser(User user);
    void deleteUserById(Long id);
}
