package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.CategoryRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý danh mục món ăn.
 *
 * Endpoints:
 *   GET    /api/categories           → Lấy tất cả danh mục
 *   GET    /api/categories/{maDm}    → Lấy chi tiết 1 danh mục
 *   POST   /api/categories           → Tạo danh mục mới
 *   PUT    /api/categories/{maDm}    → Cập nhật danh mục
 *   DELETE /api/categories/{maDm}    → Xóa danh mục
 */
@WebServlet(name = "CategoryController", urlPatterns = "/api/categories/*")
public class CategoryController extends ApiServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDm = getPathId(req);

        if (maDm == null) {
            handleFindAll(req, resp);
        } else {
            handleFindById(req, resp, maDm);
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
        String maDm = getPathId(req);
        if (maDm == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã danh mục");
            return;
        }
        handleUpdate(req, resp, maDm);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String maDm = getPathId(req);
        if (maDm == null) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST, "Thiếu mã danh mục");
            return;
        }
        handleDelete(req, resp, maDm);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleFindAll(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        // TODO: Gọi CategoryService.findAll()
        throw new UnsupportedOperationException("TODO: implement findAll");
    }

    private void handleFindById(HttpServletRequest req, HttpServletResponse resp, String maDm) throws IOException {
        // TODO: Gọi CategoryService.findById(maDm)
        throw new UnsupportedOperationException("TODO: implement findById");
    }

    private void handleCreate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CategoryRequest request = readBody(req, CategoryRequest.class);
        // TODO: Gọi CategoryService.create(request)
        throw new UnsupportedOperationException("TODO: implement create");
    }

    private void handleUpdate(HttpServletRequest req, HttpServletResponse resp, String maDm) throws IOException {
        CategoryRequest request = readBody(req, CategoryRequest.class);
        // TODO: Gọi CategoryService.update(maDm, request)
        throw new UnsupportedOperationException("TODO: implement update");
    }

    private void handleDelete(HttpServletRequest req, HttpServletResponse resp, String maDm) throws IOException {
        // TODO: Gọi CategoryService.deleteById(maDm)
        throw new UnsupportedOperationException("TODO: implement delete");
    }
}
