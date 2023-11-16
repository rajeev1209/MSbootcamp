package com.tcs.orderservice.controller;

import com.tcs.orderservice.model.Order;
import com.tcs.orderservice.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderRepository repository;

    @GetMapping("/getOrder/{id}")
    public Order getOrder(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/addOrder")
    public Order getOrder(@RequestBody Order order) {
        return repository.save(order);
    }
}
