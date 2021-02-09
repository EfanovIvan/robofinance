package com.example.robofinance.services;

import com.example.robofinance.domain.Customer;

public interface CustomerService {
    Long save(Customer customer);
    Customer find(Long id);
    Customer findByFirstNameAndLastName(String name, String lastName);
}

