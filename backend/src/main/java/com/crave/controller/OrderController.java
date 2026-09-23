package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.OrderRequest;
import com.crave.dto.request.OrderStatusRequest;
import com.crave.dto.response.OrderDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "OrderController", urlPatterns = "/api/orders/*")
public class OrderController extends ApiServlet {
    // POST /api/orders
    public ApiResponse<OrderDTO> create(OrderRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/orders/{maDh}
    public ApiResponse<OrderDTO> findById(String maDh) {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/customers/{maKh}/orders
    public ApiResponse<List<OrderDTO>> findByCustomer(String maKh) {
        throw new UnsupportedOperationException("TODO");
    }

    // PATCH /api/orders/{maDh}/status
    public ApiResponse<OrderDTO> updateStatus(String maDh, OrderStatusRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PATCH /api/orders/{maDh}/cancel
    public ApiResponse<OrderDTO> cancel(String maDh, OrderStatusRequest request) {
        throw new UnsupportedOperationException("TODO");
    }
}
