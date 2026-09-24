package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.LoginRequest;
import com.crave.dto.request.RegisterRequest;
import com.crave.dto.response.UserDTO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

/**
 * Servlet xử lý xác thực người dùng.
 *
 * Endpoints:
 *   POST /api/auth/register  → Đăng ký tài khoản mới
 *   POST /api/auth/login     → Đăng nhập hệ thống
 *   POST /api/auth/logout    → Đăng xuất (hủy session)
 */
@WebServlet(name = "AuthController", urlPatterns = "/api/auth/*")
public class AuthController extends ApiServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = getPathId(req); // "register", "login", "logout"

        if (action == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu action (register/login/logout)");
            return;
        }

        switch (action) {
            case "register" -> handleRegister(req, resp);
            case "login"    -> handleLogin(req, resp);
            case "logout"   -> handleLogout(req, resp);
            default -> sendError(resp, HttpServletResponse.SC_NOT_FOUND,
                    "Endpoint không tồn tại: /api/auth/" + action);
        }
    }

    // ─── POST /api/auth/register ─────────────────────────────────
    private void handleRegister(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        RegisterRequest request = readBody(req, RegisterRequest.class);
        // TODO: Gọi AuthService.register(request)
        throw new UnsupportedOperationException("TODO: implement register logic");
    }

    // ─── POST /api/auth/login ────────────────────────────────────
    private void handleLogin(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        LoginRequest request = readBody(req, LoginRequest.class);
        // TODO: Gọi AuthService.login(request)
        // TODO: Lưu thông tin user vào HttpSession
        throw new UnsupportedOperationException("TODO: implement login logic");
    }

    // ─── POST /api/auth/logout ───────────────────────────────────
    private void handleLogout(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
        sendSuccess(resp, "Đăng xuất thành công", null);
    }
}
