package com.example.agency.mappers;

import com.example.agency.dao.entity.Address;
import com.example.agency.dao.entity.Project;
import com.example.agency.dto.AddressDto;
import com.example.agency.dto.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectMapper {

    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDto mapModelToDto(Project project);
    Project mapDtoToModel(ProjectDto projectDto);
}
