package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.AddressRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý địa chỉ giao hàng.
 *
 * Endpoints liên quan khách hàng (nested resource):
 *   GET    /api/customers/{maKh}/addresses          → Lấy danh sách địa chỉ
 *   POST   /api/customers/{maKh}/addresses          → Thêm địa chỉ mới
 *
 * Endpoints trực tiếp:
 *   PUT    /api/addresses/{maDiaChi}                → Cập nhật địa chỉ
 *   PATCH  /api/addresses/{maDiaChi}/default        → Đặt làm địa chỉ mặc định
 *   DELETE /api/addresses/{maDiaChi}                → Xóa địa chỉ
 *
 * Lưu ý: Servlet này chỉ xử lý /api/addresses/*
 * Endpoints /api/customers/{maKh}/addresses được xử lý tại CustomerSubResourceController
 */
@WebServlet(name = "AddressController", urlPatterns = "/api/addresses/*")
public class AddressController extends ApiServlet {

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDiaChi = getPathId(req);
        if (maDiaChi == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã địa chỉ");
            return;
        }
        handleUpdate(req, resp, maDiaChi);
    }

    @Override
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDiaChi = getPathId(req);
        String subResource = getSubResource(req);

        if (maDiaChi == null || !"default".equals(subResource)) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: PATCH /api/addresses/{maDiaChi}/default");
            return;
        }
        handleSetDefault(req, resp, maDiaChi);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDiaChi = getPathId(req);
        if (maDiaChi == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã địa chỉ");
            return;
        }
        handleDelete(req, resp, maDiaChi);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maDiaChi) throws IOException {
        AddressRequest request = readBody(req, AddressRequest.class);
        // TODO: Gọi ShippingAddressService.update(maDiaChi, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleSetDefault(HttpServletRequest req, HttpServletResponse resp, String maDiaChi) throws IOException {
        // TODO: Gọi ShippingAddressService.setDefault(maDiaChi)
        throw new UnsupportedOperationException("TODO: implement setDefault");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp, String maDiaChi) throws IOException {
        // TODO: Gọi ShippingAddressService.deleteById(maDiaChi)
        throw new UnsupportedOperationException("TODO: implement delete");
    }
}
