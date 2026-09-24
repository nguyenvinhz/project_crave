package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.FoodOptionRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý tùy chọn món ăn (size, topping, mức đường, mức đá...).
 *
 * Endpoints:
 *   POST   /api/food-options              → Tạo tùy chọn mới
 *   PUT    /api/food-options/{maTuyChon}   → Cập nhật tùy chọn
 *   DELETE /api/food-options/{maTuyChon}   → Xóa tùy chọn
 */
@WebServlet(name = "FoodOptionController", urlPatterns = "/api/food-options/*")
public class FoodOptionController extends ApiServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleCreate(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maTuyChon = getPathId(req);
        if (maTuyChon == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã tùy chọn");
            return;
        }
        handleUpdate(req, resp, maTuyChon);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maTuyChon = getPathId(req);
        if (maTuyChon == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã tùy chọn");
            return;
        }
        handleDelete(req, resp, maTuyChon);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        FoodOptionRequest request = readBody(req, FoodOptionRequest.class);
        // TODO: Gọi FoodOptionService.create(request)
        throw new UnsupportedOperationException("TODO: implement create");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maTuyChon) throws IOException {
        FoodOptionRequest request = readBody(req, FoodOptionRequest.class);
        // TODO: Gọi FoodOptionService.update(maTuyChon, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp, String maTuyChon) throws IOException {
        // TODO: Gọi FoodOptionService.deleteById(maTuyChon)
        throw new UnsupportedOperationException("TODO: implement delete");
    }
}
