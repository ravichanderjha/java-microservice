package com.example.clientserviceapplication;

// ClientController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClientController {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public ClientController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/services")
    public List<String> getAllServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/products")
    public String getProducts() {
        List<ServiceInstance> instances = discoveryClient.getInstances("productservice");
        if (!instances.isEmpty()) {
            ServiceInstance productService = instances.get(0);
            String productServiceUrl = productService.getUri().toString();
            // Use productServiceUrl to communicate with ProductService API
            return "Fetching products from ProductService...";
        }
        return "ProductService is not available.";
    }

    @GetMapping("/orders")
    public String getOrders() {
        List<ServiceInstance> instances = discoveryClient.getInstances("orderservice");
        if (!instances.isEmpty()) {
            ServiceInstance orderService = instances.get(0);
            String orderServiceUrl = orderService.getUri().toString();
            // Use orderServiceUrl to communicate with OrderService API
            return "Fetching orders from OrderService...";
        }
        return "OrderService is not available.";
    }
}