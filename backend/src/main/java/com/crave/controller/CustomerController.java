package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.CustomerRequest;
import com.crave.dto.request.StatusRequest;
import com.crave.dto.response.CustomerDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "CustomerController", urlPatterns = "/api/customers/*")
public class CustomerController extends ApiServlet {
    // GET /api/customers
    public ApiResponse<List<CustomerDTO>> findAll() {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/customers/{maKh}
    public ApiResponse<CustomerDTO> findById(String maKh) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/customers/{maKh}
    public ApiResponse<CustomerDTO> update(String maKh, CustomerRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PATCH /api/customers/{maKh}/status
    public ApiResponse<CustomerDTO> changeStatus(String maKh, StatusRequest request) {
        throw new UnsupportedOperationException("TODO");
    }
}

