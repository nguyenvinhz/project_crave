package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.FoodRequest;
import com.crave.dto.request.StatusRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý món ăn.
 *
 * Endpoints:
 *   GET    /api/foods                   → Tìm kiếm / lấy danh sách món ăn
 *   GET    /api/foods/{maMon}           → Lấy chi tiết 1 món ăn
 *   GET    /api/foods/{maMon}/options   → Lấy danh sách tùy chọn của món
 *   POST   /api/foods                   → Tạo món ăn mới
 *   PUT    /api/foods/{maMon}           → Cập nhật món ăn
 *   PATCH  /api/foods/{maMon}/status    → Đổi trạng thái (DangBan/NgungBan)
 */
@WebServlet(name = "FoodController", urlPatterns = "/api/foods/*")
public class FoodController extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maMon = getPathId(req);
        String subResource = getSubResource(req);

        if (maMon == null) {
            // GET /api/foods?keyword=...&maDm=...
            handleSearch(req, resp);
        } else if ("options".equals(subResource)) {
            // GET /api/foods/{maMon}/options
            handleFindOptions(req, resp, maMon);
        } else {
            // GET /api/foods/{maMon}
            handleFindById(req, resp, maMon);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleCreate(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maMon = getPathId(req);
        if (maMon == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã món ăn");
            return;
        }
        handleUpdate(req, resp, maMon);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maMon = getPathId(req);
        String subResource = getSubResource(req);

        if (maMon == null || !"status".equals(subResource)) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: PATCH /api/foods/{maMon}/status");
            return;
        }
        handleChangeStatus(req, resp, maMon);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleSearch(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // Query params: keyword, maDm, trangThai, page, size
        // TODO: Gọi FoodService.search(...)
        throw new UnsupportedOperationException("TODO: implement search");
    }

    private void handleFindById(HttpServletRequest req, HttpServletResponse resp, String maMon) throws IOException {
        // TODO: Gọi FoodService.findById(maMon)
        throw new UnsupportedOperationException("TODO: implement findById");
    }

    private void handleFindOptions(HttpServletRequest req, HttpServletResponse resp, String maMon) throws IOException {
        // TODO: Gọi FoodOptionService.findByFood(maMon)
        throw new UnsupportedOperationException("TODO: implement findOptions");
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FoodRequest request = readBody(req, FoodRequest.class);
        // TODO: Gọi FoodService.create(request)
        throw new UnsupportedOperationException("TODO: implement create");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maMon) throws IOException {
        FoodRequest request = readBody(req, FoodRequest.class);
        // TODO: Gọi FoodService.update(maMon, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleChangeStatus(HttpServletRequest req, HttpServletResponse resp, String maMon) throws IOException {
        StatusRequest request = readBody(req, StatusRequest.class);
        // TODO: Gọi FoodService.changeStatus(maMon, request)
        throw new UnsupportedOperationException("TODO: implement changeStatus");
    }
}
