package com.crave.controller;

import com.crave.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/foods")
public class FoodController {

    @GetMapping
    public ApiResponse<Object> getAllFoods() {
        return null;
    }

    @GetMapping("/{id}")
    public ApiResponse<Object> getFoodById(@PathVariable("id") String id) {
        return null;
    }

    @PostMapping
    public ApiResponse<Object> createFood(@RequestBody Object request) {
        return null;
    }
}
