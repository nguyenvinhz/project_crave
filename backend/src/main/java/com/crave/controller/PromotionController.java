package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.PromotionRequest;
import com.crave.dto.request.ValidatePromotionRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý khuyến mãi.
 *
 * Endpoints:
 *   GET    /api/promotions              → Lấy danh sách khuyến mãi
 *   GET    /api/promotions/{maKm}       → Lấy chi tiết 1 khuyến mãi
 *   POST   /api/promotions              → Tạo khuyến mãi mới
 *   POST   /api/promotions/validate     → Kiểm tra tính hợp lệ của mã khuyến mãi
 *   PUT    /api/promotions/{maKm}       → Cập nhật khuyến mãi
 */
@WebServlet(name = "PromotionController", urlPatterns = "/api/promotions/*")
public class PromotionController extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maKm = getPathId(req);

        if (maKm == null) {
            handleFindAll(req, resp);
        } else {
            handleFindById(req, resp, maKm);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String pathId = getPathId(req);

        if ("validate".equals(pathId)) {
            // POST /api/promotions/validate
            handleValidate(req, resp);
        } else {
            // POST /api/promotions
            handleCreate(req, resp);
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maKm = getPathId(req);
        if (maKm == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã khuyến mãi");
            return;
        }
        handleUpdate(req, resp, maKm);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleFindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: Gọi PromotionService.findAll()
        throw new UnsupportedOperationException("TODO: implement findAll");
    }

    private void handleFindById(HttpServletRequest req, HttpServletResponse resp, String maKm) throws IOException {
        // TODO: Gọi PromotionService.findById(maKm)
        throw new UnsupportedOperationException("TODO: implement findById");
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        PromotionRequest request = readBody(req, PromotionRequest.class);
        // TODO: Gọi PromotionService.create(request)
        throw new UnsupportedOperationException("TODO: implement create");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maKm) throws IOException {
        PromotionRequest request = readBody(req, PromotionRequest.class);
        // TODO: Gọi PromotionService.update(maKm, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleValidate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        ValidatePromotionRequest request = readBody(req, ValidatePromotionRequest.class);
        // TODO: Gọi PromotionService.validate(request)
        throw new UnsupportedOperationException("TODO: implement validate");
    }
}
