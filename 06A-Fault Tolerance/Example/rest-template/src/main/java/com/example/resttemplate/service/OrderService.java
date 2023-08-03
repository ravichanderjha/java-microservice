package com.example.resttemplate.service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallbackMethod")
    public String getOrder(Long id) {
        // Make a REST API call to the Product Service to get product information
            String orderInfo = restTemplate.getForObject("http://order-service/orders/" + id, String.class);

        return "Order details: " + orderInfo;
    }
    public String fallbackMethod(Long id) {
        // Fallback logic in case the external service call fails
        return "Invalid value";
    }
}