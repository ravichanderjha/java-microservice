package com.example.resttemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    public String getOrder(Long id) {
        // Make a REST API call to the Product Service to get product information
        String orderInfo = restTemplate.getForObject("http://localhost:8082/orders/" + id, String.class);

        return "Order details: " + orderInfo;
    }
}