package com.tcs.customerservice.controller;

import com.tcs.customerservice.model.Customer;
import com.tcs.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
public class CustomerController{

    @Autowired
    private CustomerRepository repository;

    @GetMapping("/getCustomer/{id}")
    public Customer getOrder(@PathVariable Long id) {
        return repository.findById(id).orElse(null);
    }

    @PostMapping("/addOrder")
    public Customer getOrder(@RequestBody Customer order) {
        return repository.save(order);
    }
}