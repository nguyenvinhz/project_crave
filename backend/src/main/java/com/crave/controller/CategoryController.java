package com.crave.controller;

import com.crave.common.dto.ApiResponse;
import com.crave.common.servlet.ApiServlet;
import com.crave.dto.request.CategoryRequest;
import com.crave.dto.response.CategoryDTO;
import jakarta.servlet.annotation.WebServlet;
import java.util.List;

@WebServlet(name = "CategoryController", urlPatterns = "/api/categories/*")
public class CategoryController extends ApiServlet {
    // GET /api/categories
    public ApiResponse<List<CategoryDTO>> findAll() {
        throw new UnsupportedOperationException("TODO");
    }

    // GET /api/categories/{maDm}
    public ApiResponse<CategoryDTO> findById(String maDm) {
        throw new UnsupportedOperationException("TODO");
    }

    // POST /api/categories
    public ApiResponse<CategoryDTO> create(CategoryRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // PUT /api/categories/{maDm}
    public ApiResponse<CategoryDTO> update(String maDm, CategoryRequest request) {
        throw new UnsupportedOperationException("TODO");
    }

    // DELETE /api/categories/{maDm}
    public ApiResponse<Void> delete(String maDm) {
        throw new UnsupportedOperationException("TODO");
    }
}

