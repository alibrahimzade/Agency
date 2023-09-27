package com.example.agency.service;

import com.example.agency.dao.entity.Address;
import com.example.agency.dao.repository.AddressRepository;
import com.example.agency.dto.AddressDto;
import com.example.agency.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;

import static org.springframework.http.HttpStatus.*;


@Service
@RequiredArgsConstructor
public class AddressService {

    private final AddressRepository addressRepository;


    public ResponseEntity<?> getAddressById(Long serviceId) {
        Address address = addressRepository.findById(serviceId).orElseGet(() -> null);
        if (Objects.nonNull((address))) {
            return ResponseEntity.ok(AddressMapper.INSTANCE.mapModelToDto(address));
        }
        return ResponseEntity.status(NOT_FOUND).body("ADDRESS NOT FOUND");
    }

    public ResponseEntity<?> updateAddress(AddressDto addressDto, Long id) {
        Address address = addressRepository.getAddressById(id);
        address.setName(addressDto.getName());
        addressRepository.save(address);
    }

    public ResponseEntity<?> deleteAddress(Long id) {
        Address address = addressRepository.findById(id).orElseGet(()->null);
        return ResponseEntity.status(OK).body("SUCCESSFULLY DELETED");

    }

    public ResponseEntity<?> createAddress(Address address1) {

        Address address = addressRepository.getAddressByName(address1.getName()).orElseGet(() -> null);
        if(Objects.isNull(address)){
            Address save = addressRepository.save(address1);
            return ResponseEntity.ok(save);
        }
return ResponseEntity.status(CONFLICT).body("This Address already  is exist...")
    }

}

