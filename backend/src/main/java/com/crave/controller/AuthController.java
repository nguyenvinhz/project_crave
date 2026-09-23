package com.crave.controller;

import com.crave.dto.ApiResponse;
import com.crave.dto.RegisterRequest;
import com.crave.dto.UserDTO;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@RequestBody RegisterRequest request) {
        return null;
    }

    @PostMapping("/login")
    public ApiResponse<Object> login(@RequestBody Object request) {
        return null;
    }
}
