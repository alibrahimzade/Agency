package com.example.agency.controller;

import com.example.agency.dto.AddressDto;
import com.example.agency.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/address")
public class AddressController {

    private final AddressService addressService;

    @GetMapping("/byId/{id}")
    public ResponseEntity<?> getAddress(@PathVariable("id") Long id) {
        return addressService.getAddressById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<?> insertAddress(@RequestBody AddressDto addressDto) {
       return addressService.createAddress(addressDto);
    }

    @PutMapping("/update/{id}")
    private ResponseEntity<?> updateAddress(@RequestBody AddressDto addressDto,
                                            @PathVariable(name="id") Long id) {
        return addressService.updateAddress(addressDto, id);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAddress(@PathVariable(name = "id")Long id){
        return addressService.deleteAddress(id);
    }
}
