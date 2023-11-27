package com.learn.OrderService.controller;

import com.learn.OrderService.model.Order;
import com.learn.OrderService.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("api")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping(path = "/order/{orderId}")
    public ResponseEntity<Order> getOrderById(@PathVariable("orderId") String orderId) {
        return ResponseEntity.ok().body(orderService.getOrder(orderId));
    }

    @PostMapping(path = "/order/placeOrder")
    public ResponseEntity<Order> createOrder(@RequestBody Order order) {
        return ResponseEntity.ok().body(orderService.createOrder(order));
    }
}
