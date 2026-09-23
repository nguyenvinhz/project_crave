package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.FoodRequest;
import com.crave.dto.request.FoodSearchRequest;
import com.crave.dto.request.StatusRequest;
import com.crave.dto.response.FoodDTO;
import com.crave.dto.response.FoodOptionDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "FoodController", urlPatterns = "/api/foods/*")
public class FoodController extends ApiServlet {
    // GET /api/foods
    public ApiResponse<List<FoodDTO>> search(FoodSearchRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/foods/{maMon}
    public ApiResponse<FoodDTO> findById(String maMon) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/foods
    public ApiResponse<FoodDTO> create(FoodRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/foods/{maMon}
    public ApiResponse<FoodDTO> update(String maMon, FoodRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PATCH /api/foods/{maMon}/status
    public ApiResponse<FoodDTO> changeStatus(String maMon, StatusRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/foods/{maMon}/options
    public ApiResponse<List<FoodOptionDTO>> findOptions(String maMon) {
        throw new UnsupportedOperationException("TODO");
    }
}

