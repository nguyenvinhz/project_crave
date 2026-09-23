package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.PromotionRequest;
import com.crave.dto.request.ValidatePromotionRequest;
import com.crave.dto.response.PromotionDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "PromotionController", urlPatterns = "/api/promotions/*")
public class PromotionController extends ApiServlet {
    // GET /api/promotions
    public ApiResponse<List<PromotionDTO>> findAll() {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/promotions/{maKm}
    public ApiResponse<PromotionDTO> findById(String maKm) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/promotions
    public ApiResponse<PromotionDTO> create(PromotionRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/promotions/{maKm}
    public ApiResponse<PromotionDTO> update(String maKm, PromotionRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/promotions/validate
    public ApiResponse<PromotionDTO> validate(ValidatePromotionRequest request) {
        throw new UnsupportedOperationException("TODO");
    }
}

