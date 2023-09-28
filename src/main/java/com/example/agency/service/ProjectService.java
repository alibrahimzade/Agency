package com.example.agency.service;

import com.example.agency.dao.entity.Project;
import com.example.agency.dao.repository.ProjectRepository;
import com.example.agency.dto.ProjectDto;
import com.example.agency.mappers.AddressMapper;
import com.example.agency.mappers.ProjectMapper;
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
public class ProjectService {
    private final ProjectRepository projectRepository;

    public ResponseEntity<?> getProjectById(Long id){
        Project project = projectRepository.findById(id).orElseGet(() -> null);
        if (Objects.nonNull(project)){
            return ResponseEntity.ok(ProjectMapper.INSTANCE.mapModelToDto(project));
        }
        return ResponseEntity.status(NOT_FOUND).body("This project does not exist.");
    }

    public ResponseEntity<?> updateProject(ProjectDto projectDto,Long id){
        Project project = projectRepository.findById(id).orElseGet(() -> null);
        if (Objects.nonNull(project)){
            Project updated = ProjectMapper.INSTANCE.mapDtoToModel(projectDto);
            updated.setName(projectDto.getName());
            projectRepository.save(updated);
            return ResponseEntity.status(OK).body("Project is updated");
        }
        return ResponseEntity.status(NOT_FOUND).body("This project does not exist");
    }

    public ResponseEntity<?> createProject(ProjectDto projectDto){
        Project projectName = projectRepository.getProjectByName(projectDto.getName()).orElseGet(() -> null);
        if (Objects.isNull(projectName)){
            Project project = ProjectMapper.INSTANCE.mapDtoToModel(projectDto);
            projectRepository.save(project);
            return ResponseEntity.ok(SAVING);
        }
        return ResponseEntity.status(CONFLICT).body("The project already exist.");
    }

    public ResponseEntity<?> deleteProject(Long id){
        Optional<Project> projectId = projectRepository.findById(id);
        if (Objects.nonNull(projectId)){
            projectRepository.deleteById(id);
            return ResponseEntity.status(OK).body(DELETED);
        }
        return ResponseEntity.status(NOT_FOUND).body("This projects does not exist.");
    }
}
