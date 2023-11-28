package com.todo_management_system.contoller;

import com.todo_management_system.dto.LoginDto;
import com.todo_management_system.dto.RegisterDto;
import com.todo_management_system.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/auth")
@CrossOrigin("*")
public class AuthController {

    private AuthService authService;


    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) {
        String response = authService.register(registerDto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping("login")
    public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

        String response = authService.login(loginDto);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
