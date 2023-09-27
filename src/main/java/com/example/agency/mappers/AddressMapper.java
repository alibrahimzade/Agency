package com.example.agency.mappers;

import com.example.agency.dao.entity.Address;
import com.example.agency.dto.AddressDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AddressMapper {
    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    AddressDto mapModelToDto(Address address);
    Address mapDtoToModel(AddressDto addressDto);
}
