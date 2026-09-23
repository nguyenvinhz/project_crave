package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.StaffRequest;
import com.crave.dto.request.StatusRequest;
import com.crave.dto.response.StaffDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "StaffController", urlPatterns = "/api/staff/*")
public class StaffController extends ApiServlet {
    // GET /api/staff
    public ApiResponse<List<StaffDTO>> findAll() {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/staff/{maNv}
    public ApiResponse<StaffDTO> findById(String maNv) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/staff
    public ApiResponse<StaffDTO> create(StaffRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/staff/{maNv}
    public ApiResponse<StaffDTO> update(String maNv, StaffRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PATCH /api/staff/{maNv}/status
    public ApiResponse<StaffDTO> changeStatus(String maNv, StatusRequest request) {
        throw new UnsupportedOperationException("TODO");
    }
}

