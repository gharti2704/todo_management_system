package com.todo_management_system.service;

import com.todo_management_system.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    void deleteUserById(Long id);
}
