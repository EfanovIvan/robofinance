package com.example.robofinance.services;

import com.example.robofinance.domain.Address;
import com.example.robofinance.exception.AddressNotFoundException;
import com.example.robofinance.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class AddressServiceImpl implements AddressService{

    private AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Transactional
    public Long save( Address address) {

        return addressRepository.save(address).getId();
    }

    @Override
    public Address find(Long id) {
        return addressRepository.findById(id).orElseThrow(AddressNotFoundException::new);
    }

}