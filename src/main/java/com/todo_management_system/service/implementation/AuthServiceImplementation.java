package com.todo_management_system.service.implementation;

import com.todo_management_system.dto.JwtAuthResponse;
import com.todo_management_system.dto.LoginDto;
import com.todo_management_system.dto.RegisterDto;
import com.todo_management_system.entity.Role;
import com.todo_management_system.entity.User;
import com.todo_management_system.exception.TodoAPIException;
import com.todo_management_system.repository.RoleRepository;
import com.todo_management_system.repository.UserRepository;
import com.todo_management_system.security.JwtTokenProvider;
import com.todo_management_system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@AllArgsConstructor
public class AuthServiceImplementation implements AuthService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider jwtTokenProvider;

//    @Override
//    public String toString() {
//    	return registerDto.toString();
//    }

    @Override
    public String register(RegisterDto registerDto) {

    // check if the user already exists in the db
        if(userRepository.existsByUsername(registerDto.getUsername())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Existing user found with that username, please login.");
        }

        if(userRepository.existsByEmail(registerDto.getEmail())) {
            throw new TodoAPIException(HttpStatus.BAD_REQUEST, "Existing user found with that email, please login.");
        }

        User user = new User();
        user.setName(registerDto.getName());
        user.setUsername(registerDto.getUsername());
        user.setEmail(registerDto.getEmail());
        user.setPassword(passwordEncoder.encode(registerDto.getPassword()));

        Set<Role> roles = new HashSet<>();
        roles.add(roleRepository.findByName(registerDto.getRole()));

        user.setRoles(roles);
        userRepository.save(user);

        return "User registered successfully.";
    }

    @Override
    public JwtAuthResponse login(LoginDto loginDto) {
       Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsernameOrEmail(),
                loginDto.getPassword()
        ));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        User user = userRepository.findByUsernameOrEmail(loginDto.getUsernameOrEmail(), loginDto.getUsernameOrEmail())
                .orElseThrow(() -> new TodoAPIException(HttpStatus.BAD_REQUEST, "User not found."));

        Role role = user.getRoles().stream().findFirst().orElseThrow(() -> new TodoAPIException(HttpStatus.BAD_REQUEST, "Role not found."));

        JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
        jwtAuthResponse.setJwtToken(jwtTokenProvider.generateJwtToken(authentication));
        jwtAuthResponse.setRole(role.getName());
        jwtAuthResponse.setName(user.getName());

        return jwtAuthResponse;
    }
}
