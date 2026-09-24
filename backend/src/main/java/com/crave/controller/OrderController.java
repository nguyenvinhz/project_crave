package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.OrderRequest;
import com.crave.dto.request.OrderStatusRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý đơn hàng.
 *
 * Endpoints:
 *   POST   /api/orders                   → Tạo đơn hàng mới từ giỏ hàng
 *   GET    /api/orders/{maDh}            → Xem chi tiết đơn hàng
 *   GET    /api/orders/{maDh}/history    → Xem lịch sử trạng thái đơn hàng
 *   PATCH  /api/orders/{maDh}/status     → Cập nhật trạng thái (nhân viên)
 *   PATCH  /api/orders/{maDh}/cancel     → Hủy đơn (khách hàng, khi ChoXacNhan)
 *
 * Endpoint liên quan khách hàng:
 *   GET    /api/customers/{maKh}/orders  → Xem danh sách đơn của 1 khách
 *   (được xử lý bởi CustomerOrderController riêng hoặc tại đây qua query param)
 */
@WebServlet(name = "OrderController", urlPatterns = "/api/orders/*")
public class OrderController extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDh = getPathId(req);
        String subResource = getSubResource(req);

        if (maDh == null) {
            // GET /api/orders → Không hỗ trợ (danh sách tất cả đơn)
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Vui lòng chỉ định mã đơn hàng hoặc sử dụng /api/customers/{maKh}/orders");
            return;
        }

        if ("history".equals(subResource)) {
            // GET /api/orders/{maDh}/history
            handleGetHistory(req, resp, maDh);
        } else {
            // GET /api/orders/{maDh}
            handleFindById(req, resp, maDh);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        handleCreate(req, resp);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDh = getPathId(req);
        String subResource = getSubResource(req);

        if (maDh == null || subResource == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: PATCH /api/orders/{maDh}/status hoặc /cancel");
            return;
        }

        switch (subResource) {
            case "status" -> handleUpdateStatus(req, resp, maDh);
            case "cancel" -> handleCancel(req, resp, maDh);
            default -> sendError(resp, HttpServletResponse.SC_NOT_FOUND,
                    "Sub-resource không tồn tại: " + subResource);
        }
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleFindById(HttpServletRequest req, HttpServletResponse resp, String maDh) throws IOException {
        // TODO: Gọi OrderService.findById(maDh)
        throw new UnsupportedOperationException("TODO: implement findById");
    }

    private void handleGetHistory(HttpServletRequest req, HttpServletResponse resp, String maDh) throws IOException {
        // TODO: Gọi OrderHistoryService.findByOrder(maDh)
        throw new UnsupportedOperationException("TODO: implement getHistory");
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        OrderRequest request = readBody(req, OrderRequest.class);
        // TODO: Gọi OrderService.create(request)
        throw new UnsupportedOperationException("TODO: implement create");
    }

    private void handleUpdateStatus(HttpServletRequest req, HttpServletResponse resp, String maDh) throws IOException {
        OrderStatusRequest request = readBody(req, OrderStatusRequest.class);
        // TODO: Gọi OrderService.updateStatus(maDh, request)
        throw new UnsupportedOperationException("TODO: implement updateStatus");
    }

    private void handleCancel(HttpServletRequest req, HttpServletResponse resp, String maDh) throws IOException {
        OrderStatusRequest request = readBody(req, OrderStatusRequest.class);
        // TODO: Gọi OrderService.cancel(maDh, request)
        throw new UnsupportedOperationException("TODO: implement cancel");
    }
}
