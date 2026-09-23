package com.crave.controller;

import com.crave.dto.ApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    @PostMapping("/checkout")
    public ApiResponse<Object> checkout(@RequestBody Object request) {
        return null;
    }

    @GetMapping
    public ApiResponse<Object> getOrders() {
        return null;
    }
}
