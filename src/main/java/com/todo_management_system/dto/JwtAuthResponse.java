package com.todo_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JwtAuthResponse {

    private String jwtToken;
    private String tokenType = "Bearer";
    private String name;
    private String role;

}
