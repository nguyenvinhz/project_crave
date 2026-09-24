package com.crave.controller;

import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.CartItemRequest;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet quản lý giỏ hàng.
 *
 * Lưu ý: Servlet này xử lý cả đường dẫn /api/cart/* VÀ phối hợp với
 * AddressController cho /api/customers/{maKh}/cart (xem bên dưới).
 *
 * Endpoints:
 *   POST   /api/cart/items              → Thêm món vào giỏ hàng
 *   PUT    /api/cart/items/{maCtgh}     → Cập nhật số lượng món trong giỏ
 *   DELETE /api/cart/items/{maCtgh}     → Xóa 1 món khỏi giỏ
 *
 * Endpoints liên quan khách hàng (xử lý tại CustomerCartController):
 *   GET    /api/customers/{maKh}/cart   → Lấy giỏ hàng của khách
 *   DELETE /api/customers/{maKh}/cart   → Xóa toàn bộ giỏ hàng
 */
@WebServlet(name = "CartController", urlPatterns = "/api/cart/*")
public class CartController extends ApiServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String subResource = getPathId(req); // "items"

        if (!"items".equals(subResource)) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: POST /api/cart/items");
            return;
        }
        handleAddItem(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // PUT /api/cart/items/{maCtgh}
        String[] segments = getPathSegments(req);
        if (segments.length < 2 || !"items".equals(segments[0])) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: PUT /api/cart/items/{maCtgh}");
            return;
        }
        handleUpdateItem(req, resp, segments[1]);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // DELETE /api/cart/items/{maCtgh}
        String[] segments = getPathSegments(req);
        if (segments.length < 2 || !"items".equals(segments[0])) {
            sendError(resp, HttpServletResponse.SC_BAD_REQUEST,
                    "Endpoint không hợp lệ. Sử dụng: DELETE /api/cart/items/{maCtgh}");
            return;
        }
        handleDeleteItem(req, resp, segments[1]);
    }

    // ─── Handler Methods ─────────────────────────────────────────

    private void handleAddItem(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        CartItemRequest request = readBody(req, CartItemRequest.class);
        // TODO: Gọi CartService.addItem(request)
        throw new UnsupportedOperationException("TODO: implement addItem");
    }

    private void handleUpdateItem(HttpServletRequest req, HttpServletResponse resp, String maCtgh) throws IOException {
        CartItemRequest request = readBody(req, CartItemRequest.class);
        // TODO: Gọi CartService.updateItem(maCtgh, request)
        throw new UnsupportedOperationException("TODO: implement updateItem");
    }

    private void handleDeleteItem(HttpServletRequest req, HttpServletResponse resp, String maCtgh) throws IOException {
        // TODO: Gọi CartService.deleteItem(maCtgh)
        throw new UnsupportedOperationException("TODO: implement deleteItem");
    }
}
