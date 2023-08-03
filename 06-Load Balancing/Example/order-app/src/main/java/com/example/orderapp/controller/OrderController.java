package com.example.orderapp.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @RequestMapping("/{id}")
    public String getOrderById(@PathVariable Long id) {
        // Retrieve order details from the database or any other data source
        return "Order with ID: " + id;
    }
}
