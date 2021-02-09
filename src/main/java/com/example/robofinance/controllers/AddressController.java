package com.example.robofinance.controllers;

import com.example.robofinance.domain.Address;
import com.example.robofinance.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/address")

public class AddressController {

    private AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping
    public void addAddress(@RequestBody Address address)
    {
        addressService.save(address);
    }

    @GetMapping("/{id}")
    public Address find(@PathVariable Long id)
    {
        return addressService.find(id);
    }

    @PutMapping
    public Long update(@RequestBody Address address) {
        return addressService.save(address);
    }


}