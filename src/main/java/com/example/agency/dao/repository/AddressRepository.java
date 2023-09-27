package com.example.agency.dao.repository;

import com.example.agency.dao.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    Optional<Address> getAddressByName(String name);
}
