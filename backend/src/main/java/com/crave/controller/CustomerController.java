package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.CustomerRequest;
import com.crave.dto.request.StatusRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý khách hàng.
 *
 * Endpoints:
 *   GET    /api/customers              → Lấy danh sách tất cả khách hàng
 *   GET    /api/customers/{maKh}       → Lấy thông tin chi tiết 1 khách hàng
 *   PUT    /api/customers/{maKh}       → Cập nhật thông tin khách hàng
 *   PATCH  /api/customers/{maKh}/status → Thay đổi trạng thái (khóa/kích hoạt)
 */
@WebServlet(name = "CustomerController", urlPatterns = "/api/customers/*")
public class CustomerController extends ApiServlet {

    // ─── GET /api/customers  |  GET /api/customers/{maKh} ────────
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maKh = getPathId(req);

        if (maKh == null) {
            // GET /api/customers → Danh sách
            handleFindAll(req, resp);
        } else {
            // GET /api/customers/{maKh} → Chi tiết
            handleFindById(req, resp, maKh);
        }
    }

    // ─── PUT /api/customers/{maKh} ───────────────────────────────
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maKh = getPathId(req);
        if (maKh == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã khách hàng");
            return;
        }
        handleUpdate(req, resp, maKh);
    }

    // ─── PATCH /api/customers/{maKh}/status ──────────────────────
    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maKh = getPathId(req);
        String subResource = getSubResource(req);

        if (maKh == null || !"status".equals(subResource)) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: PATCH /api/customers/{maKh}/status");
            return;
        }
        handleChangeStatus(req, resp, maKh);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleFindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: Gọi CustomerService.findAll()
        throw new UnsupportedOperationException("TODO: implement findAll");
    }

    private void handleFindById(HttpServletRequest req, HttpServletResponse resp, String maKh) throws IOException {
        // TODO: Gọi CustomerService.findById(maKh)
        throw new UnsupportedOperationException("TODO: implement findById");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maKh) throws IOException {
        CustomerRequest request = readBody(req, CustomerRequest.class);
        // TODO: Gọi CustomerService.update(maKh, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleChangeStatus(HttpServletRequest req, HttpServletResponse resp, String maKh) throws IOException {
        StatusRequest request = readBody(req, StatusRequest.class);
        // TODO: Gọi CustomerService.changeStatus(maKh, request)
        throw new UnsupportedOperationException("TODO: implement changeStatus");
    }
}
