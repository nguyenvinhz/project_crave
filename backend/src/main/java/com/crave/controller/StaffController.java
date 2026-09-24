package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.StaffRequest;
import com.crave.dto.request.StatusRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý nhân viên.
 *
 * Endpoints:
 *   GET    /api/staff              → Lấy danh sách nhân viên
 *   GET    /api/staff/{maNv}       → Lấy thông tin chi tiết 1 nhân viên
 *   POST   /api/staff              → Tạo nhân viên mới
 *   PUT    /api/staff/{maNv}       → Cập nhật thông tin nhân viên
 *   PATCH  /api/staff/{maNv}/status → Cập nhật trạng thái làm việc
 */
@WebServlet(name = "StaffController", urlPatterns = "/api/staff/*")
public class StaffController extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maNv = getPathId(req);

        if (maNv == null) {
            handleFindAll(req, resp);
        } else {
            handleFindById(req, resp, maNv);
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
        String maNv = getPathId(req);
        if (maNv == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã nhân viên");
            return;
        }
        handleUpdate(req, resp, maNv);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maNv = getPathId(req);
        String subResource = getSubResource(req);

        if (maNv == null || !"status".equals(subResource)) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: PATCH /api/staff/{maNv}/status");
            return;
        }
        handleChangeStatus(req, resp, maNv);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleFindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: Gọi StaffService.findAll()
        throw new UnsupportedOperationException("TODO: implement findAll");
    }

    private void handleFindById(HttpServletRequest req, HttpServletResponse resp, String maNv) throws IOException {
        // TODO: Gọi StaffService.findById(maNv)
        throw new UnsupportedOperationException("TODO: implement findById");
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StaffRequest request = readBody(req, StaffRequest.class);
        // TODO: Gọi StaffService.create(request)
        throw new UnsupportedOperationException("TODO: implement create");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maNv) throws IOException {
        StaffRequest request = readBody(req, StaffRequest.class);
        // TODO: Gọi StaffService.update(maNv, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleChangeStatus(HttpServletRequest req, HttpServletResponse resp, String maNv) throws IOException {
        StatusRequest request = readBody(req, StatusRequest.class);
        // TODO: Gọi StaffService.changeStatus(maNv, request)
        throw new UnsupportedOperationException("TODO: implement changeStatus");
    }
}
