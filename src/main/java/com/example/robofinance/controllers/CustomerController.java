package com.example.robofinance.controllers;

import com.example.robofinance.domain.Customer;
import com.example.robofinance.services.CustomerService;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/customer")
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Long save(@RequestBody Customer customer)
    {
        return customerService.save(customer);
    }

    @GetMapping
    public Customer findByFirstAndLastName(@RequestParam String firstName, @RequestParam String lastName)
    {
        return customerService.findByFirstNameAndLastName(firstName, lastName);
    }

    @GetMapping("/{id}")
    public Customer find(@PathVariable Long id)
    {
        return customerService.find(id);
    }

    @PutMapping("/{id}")
    public Long refresh(@PathVariable Long id, @RequestBody Customer customer)
    {
        customer.setId(id);
        return customerService.save(customer);
    }
}
