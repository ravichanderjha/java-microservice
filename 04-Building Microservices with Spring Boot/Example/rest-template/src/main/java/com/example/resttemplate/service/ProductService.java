package com.example.resttemplate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ProductService {

    @Autowired
    private RestTemplate restTemplate;

    public String getProduct(Long id) {
        // Make a REST API call to the Product Service to get product information
        String productInfo = restTemplate.getForObject("http://localhost:8081/products/" + id, String.class);

        return "Product details with Product info: " + productInfo;
    }
}