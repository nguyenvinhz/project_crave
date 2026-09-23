package com.crave.controller;

import com.crave.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @GetMapping
    public ApiResponse<Object> getCart() {
        return null;
    }

    @PostMapping("/items")
    public ApiResponse<Object> addItem(@RequestBody Object request) {
        return null;
    }
}
