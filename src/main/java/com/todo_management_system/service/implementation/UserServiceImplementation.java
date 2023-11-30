package com.todo_management_system.service.implementation;

import com.todo_management_system.dto.UserDto;
import com.todo_management_system.entity.Role;
import com.todo_management_system.entity.Todo;
import com.todo_management_system.entity.User;
import com.todo_management_system.repository.UserRepository;
import com.todo_management_system.service.UserService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImplementation implements UserService {

    private UserRepository userRepository;
    private ModelMapper modelMapper;

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream().map((user) -> modelMapper.map(user, UserDto.class)).collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void deleteUserById(Long id) {
        // delete user role from join table user_roles first
        User user =  userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.getRoles().removeIf(role -> role.getId().equals(id));
        userRepository.deleteById(id);
    }
}
