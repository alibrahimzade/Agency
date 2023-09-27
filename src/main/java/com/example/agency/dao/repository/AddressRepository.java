package com.example.agency.dao.repository;

import com.example.agency.dao.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AddressRepository extends JpaRepository<Address, Long> {
    Address getAddressById(Long id);
    Optional<Address> getAddressByName(String name);

}
