package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.response.OrderStatusHistoryDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "OrderHistoryController", urlPatterns = "/api/order-history/*")
public class OrderHistoryController extends ApiServlet {
    // GET /api/orders/{maDh}/history
    public ApiResponse<List<OrderStatusHistoryDTO>> findByOrder(String maDh) {
        throw new UnsupportedOperationException("TODO");
    }
}
