package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.AddressRequest;
import com.crave.dto.response.AddressDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "AddressController", urlPatterns = "/api/addresses/*")
public class AddressController extends ApiServlet {
    // GET /api/customers/{maKh}/addresses
    public ApiResponse<List<AddressDTO>> findByCustomer(String maKh) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/customers/{maKh}/addresses
    public ApiResponse<AddressDTO> create(String maKh, AddressRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/addresses/{maDiaChi}
    public ApiResponse<AddressDTO> update(String maDiaChi, AddressRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PATCH /api/addresses/{maDiaChi}/default
    public ApiResponse<AddressDTO> setDefault(String maDiaChi) {
        throw new UnsupportedOperationException("TODO");
    }

    // DELETE /api/addresses/{maDiaChi}
    public ApiResponse<Void> delete(String maDiaChi) {
        throw new UnsupportedOperationException("TODO");
    }
}
