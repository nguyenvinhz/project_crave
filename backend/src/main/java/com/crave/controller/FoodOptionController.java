package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.FoodOptionRequest;
import com.crave.dto.response.FoodOptionDTO;
import jakarta.servlet.annotation.WebServlet;

@WebServlet(name = "FoodOptionController", urlPatterns = "/api/food-options/*")
public class FoodOptionController extends ApiServlet {
    // POST /api/food-options
    public ApiResponse<FoodOptionDTO> create(FoodOptionRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/food-options/{maTuyChon}
    public ApiResponse<FoodOptionDTO> update(String maTuyChon, FoodOptionRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // DELETE /api/food-options/{maTuyChon}
    public ApiResponse<Void> delete(String maTuyChon) {
        throw new UnsupportedOperationException("TODO");
    }
}

