package com.example.robofinance.services;

import com.example.robofinance.domain.Customer;
import com.example.robofinance.exception.CustomerNotFoundException;
import com.example.robofinance.repository.AddressRepository;
import com.example.robofinance.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{

    private CustomerRepository customerRepository;
    private AddressRepository addressRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Long save(Customer customer) {
        addressRepository.save(customer.getActualAddress());
        addressRepository.save(customer.getRegisteredAddress());
        return customerRepository.save(customer).getId();
    }

    @Transactional
    public Customer find(Long id) {
        return customerRepository.findById(id).orElseThrow(CustomerNotFoundException::new);
    }

    @Transactional
    public Customer findByFirstNameAndLastName(String firstName, String lastName) {
        return customerRepository.findByFirstNameAndLastName(firstName, lastName).orElseThrow(CustomerNotFoundException::new);
    }

}