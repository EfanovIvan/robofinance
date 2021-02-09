package com.example.robofinance.services;

import com.example.robofinance.domain.Address;

public interface AddressService {
    Long save(Address address);
    Address find(Long id);
}
