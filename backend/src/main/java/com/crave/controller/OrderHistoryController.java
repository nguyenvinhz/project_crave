package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý lịch sử trạng thái đơn hàng.
 *
 * Endpoints:
 *   GET /api/order-history/{maDh}  → Xem lịch sử các trạng thái của đơn hàng
 */
@WebServlet(name = "OrderHistoryController", urlPatterns = "/api/order-history/*")
public class OrderHistoryController extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDh = getPathId(req);

        if (maDh == null || maDh.isBlank()) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã đơn hàng trong URL: /api/order-history/{maDh}");
            return;
        }

        handleFindByOrder(req, resp, maDh);
    }

    private void handleFindByOrder(HttpServletRequest req, HttpServletResponse resp, String maDh)
            throws IOException {
        // TODO: Gọi OrderHistoryService.findByOrder(maDh)
        throw new UnsupportedOperationException("TODO: implement OrderHistoryService.findByOrder");
    }
}
