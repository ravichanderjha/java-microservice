package com.example.resttemplate.controller;

import com.example.resttemplate.service.OrderService;
import com.example.resttemplate.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("rest")
public class RestTemControler {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;
    @RequestMapping("/order/{id}")
    Object getOrder(@PathVariable long id){
        return orderService.getOrder(id);
    }
    @RequestMapping("/product/{id}")
    Object getProduct(@PathVariable long id){
        return productService.getProduct(id);
    }
}
