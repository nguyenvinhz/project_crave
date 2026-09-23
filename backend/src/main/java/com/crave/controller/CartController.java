package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.CartItemRequest;
import com.crave.dto.response.CartDTO;
import com.crave.dto.response.CartItemDTO;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "CartController", urlPatterns = "/api/cart/*")
public class CartController extends ApiServlet {
    // GET /api/customers/{maKh}/cart
    public ApiResponse<CartDTO> findByCustomer(String maKh) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/cart/items
    public ApiResponse<CartItemDTO> addItem(CartItemRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/cart/items/{maCtgh}
    public ApiResponse<CartItemDTO> updateItem(String maCtgh, CartItemRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // DELETE /api/cart/items/{maCtgh}
    public ApiResponse<Void> removeItem(String maCtgh) {
        throw new UnsupportedOperationException("TODO");
    }

    // DELETE /api/customers/{maKh}/cart
    public ApiResponse<Void> clear(String maKh) {
        throw new UnsupportedOperationException("TODO");
    }
}
