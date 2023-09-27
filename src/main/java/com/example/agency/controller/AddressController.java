package com.example.agency.controller;

import com.example.agency.dao.entity.Address;
import com.example.agency.dto.AddressDto;
import com.example.agency.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/{address-id}")
    public ResponseEntity<?> getAddress(@PathVariable("address-id") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping
    public ResponseEntity<?> insertAddress(@RequestBody AddressDto addressDto) {
       return addressService.createAddress(addressDto);
    }

    @PutMapping("{/id}")
    private ResponseEntity<?> updateAddress(@RequestBody AddressDto addressDto, @PathVariable("id") Long id) {
        addressService.updateAddress(addressDto, id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable("id")Long id){

    }

}
