package com.example.agency.service;

import com.example.agency.dao.entity.Address;
import com.example.agency.dao.repository.AddressRepository;
import com.example.agency.dto.AddressDto;
import com.example.agency.mappers.AddressMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Objects;
import java.util.Optional;

import static org.hibernate.engine.spi.Status.DELETED;
import static org.hibernate.engine.spi.Status.SAVING;
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

        Address address = addressRepository.findById(id).orElseGet(() -> null);
        if (Objects.nonNull(address)){
            Address updated = AddressMapper.INSTANCE.mapDtoToModel(addressDto);
            updated.setName(addressDto.getName());
            addressRepository.save(updated);
            return ResponseEntity.status(OK).body("Address is updated");
        }
        return ResponseEntity.status(NOT_FOUND).body("Address doesn't exist");
    }

    public ResponseEntity<?> deleteAddress(Long id) {
        Optional<Address> addressId = addressRepository.findById(id);
        if (Objects.nonNull(addressId)){
            addressRepository.deleteById(id);
            return ResponseEntity.status(OK).body(DELETED);
        }
        return ResponseEntity.status(NOT_FOUND).body("Address doesn't exist.");
    }

    public ResponseEntity<?> createAddress(AddressDto addressDto) {
        Address addressName = addressRepository.getAddressByName(addressDto.getName()).orElseGet(() -> null);
        if (Objects.isNull(addressName)){
            Address address = AddressMapper.INSTANCE.mapDtoToModel(addressDto);
            addressRepository.save(address);
            return ResponseEntity.ok(SAVING);
        }
        return ResponseEntity.status(CONFLICT).body("Address is already exist.");
    }

}

