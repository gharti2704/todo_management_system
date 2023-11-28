package com.todo_management_system.service;

import com.todo_management_system.dto.LoginDto;
import com.todo_management_system.dto.RegisterDto;

public interface AuthService {

    String register(RegisterDto registerDto);
    String login(LoginDto loginDto);
}
