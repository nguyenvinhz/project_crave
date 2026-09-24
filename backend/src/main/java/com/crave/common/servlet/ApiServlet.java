package com.crave.common.servlet;

import com.crave.common.dto.ApiResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;

/**
 * Lớp Servlet cơ sở cho tất cả API Controllers.
 * <p>
 * Cung cấp các tiện ích:
 * <ul>
 *   <li>Đọc JSON request body và chuyển đổi thành DTO</li>
 *   <li>Gửi JSON response với status code phù hợp</li>
 *   <li>Trích xuất path segments từ pathInfo</li>
 *   <li>Hỗ trợ PATCH method qua override doService</li>
 *   <li>Xử lý CORS headers</li>
 * </ul>
 */
public abstract class ApiServlet extends HttpServlet {

    protected static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
    }

    // ─── Hỗ trợ PATCH method ────────────────────────────────────

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Thiết lập encoding cho request/response
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String method = req.getMethod().toUpperCase();

        if ("PATCH".equals(method)) {
            doPatch(req, resp);
        } else {
            super.service(req, resp);
        }
    }

    /**
     * Xử lý HTTP PATCH request. Subclass cần override nếu có endpoint PATCH.
     */
    protected void doPatch(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        sendError(resp, HttpServletResponse.SC_METHOD_NOT_ALLOWED, "PATCH method không được hỗ trợ");
    }

    // ─── Đọc JSON Request Body ──────────────────────────────────

    /**
     * Đọc body của request và parse thành object kiểu T.
     *
     * @param req   HttpServletRequest
     * @param clazz Class đích để deserialize
     * @return Object đã được parse
     * @throws IOException nếu không đọc/parse được body
     */
    protected <T> T readBody(HttpServletRequest req, Class<T> clazz) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        }
        return objectMapper.readValue(sb.toString(), clazz);
    }

    // ─── Gửi JSON Response ──────────────────────────────────────

    /**
     * Gửi response JSON thành công với status 200.
     */
    protected void sendJson(HttpServletResponse resp, Object data) throws IOException {
        sendJson(resp, HttpServletResponse.SC_OK, data);
    }

    /**
     * Gửi response JSON với status code tùy chỉnh.
     */
    protected void sendJson(HttpServletResponse resp, int statusCode, Object data) throws IOException {
        resp.setStatus(statusCode);
        resp.setContentType("application/json;charset=UTF-8");
        objectMapper.writeValue(resp.getWriter(), data);
    }

    /**
     * Gửi response lỗi chuẩn hóa.
     */
    protected void sendError(HttpServletResponse resp, int statusCode, String message) throws IOException {
        sendJson(resp, statusCode, ApiResponse.error(message));
    }

    /**
     * Gửi response thành công chuẩn hóa (HTTP 200).
     */
    protected <T> void sendSuccess(HttpServletResponse resp, T data) throws IOException {
        sendJson(resp, HttpServletResponse.SC_OK, ApiResponse.success(data));
    }

    /**
     * Gửi response thành công với thông điệp tùy chỉnh.
     */
    protected <T> void sendSuccess(HttpServletResponse resp, String message, T data) throws IOException {
        sendJson(resp, HttpServletResponse.SC_OK, ApiResponse.success(message, data));
    }

    /**
     * Gửi response thành công với status 201 Created.
     */
    protected <T> void sendCreated(HttpServletResponse resp, T data) throws IOException {
        sendJson(resp, HttpServletResponse.SC_CREATED, ApiResponse.success("Tạo mới thành công", data));
    }

    // ─── Path Parsing Utilities ─────────────────────────────────

    /**
     * Lấy pathInfo và loại bỏ dấu "/" đầu/cuối, trả về mảng segments.
     * <p>
     * Ví dụ: pathInfo = "/KH001/addresses" → ["KH001", "addresses"]
     *         pathInfo = null hoặc "/" → mảng rỗng []
     *
     * @return mảng các path segments
     */
    protected String[] getPathSegments(HttpServletRequest req) {
        String pathInfo = req.getPathInfo();
        if (pathInfo == null || pathInfo.equals("/")) {
            return new String[0];
        }
        // Loại bỏ "/" đầu tiên rồi split
        return pathInfo.substring(1).split("/");
    }

    /**
     * Lấy segment đầu tiên của pathInfo (thường là ID resource).
     *
     * @return segment đầu tiên hoặc null nếu không có
     */
    protected String getPathId(HttpServletRequest req) {
        String[] segments = getPathSegments(req);
        return segments.length > 0 ? segments[0] : null;
    }

    /**
     * Lấy segment thứ hai của pathInfo (thường là sub-resource).
     * Ví dụ: /KH001/addresses → "addresses"
     *
     * @return segment thứ hai hoặc null nếu không có
     */
    protected String getSubResource(HttpServletRequest req) {
        String[] segments = getPathSegments(req);
        return segments.length > 1 ? segments[1] : null;
    }

    /**
     * Lấy query parameter từ request.
     */
    protected String getParam(HttpServletRequest req, String name) {
        return req.getParameter(name);
    }

    /**
     * Lấy query parameter dạng Integer.
     */
    protected Integer getIntParam(HttpServletRequest req, String name) {
        String value = req.getParameter(name);
        if (value == null || value.isBlank()) return null;
        try {
            return Integer.parseInt(value);
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
