package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.LoginRequest;
import com.crave.dto.request.RegisterRequest;
import com.crave.dto.response.UserDTO;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "AuthController", urlPatterns = "/api/auth/*")
public class AuthController extends ApiServlet {
    // POST /api/auth/register
    public ApiResponse<UserDTO> register(RegisterRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/auth/login
    public ApiResponse<UserDTO> login(LoginRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/auth/logout
    public ApiResponse<Void> logout() {
        throw new UnsupportedOperationException("TODO");
    }
}

