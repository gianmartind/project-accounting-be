package com.gmd.project_accounting_be.modules.project.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.gmd.project_accounting_be.modules.project.dtos.GetProjectDTO;
import com.gmd.project_accounting_be.modules.project.dtos.UpdateProjectDTO;
import com.gmd.project_accounting_be.modules.project.entities.Project;
import com.gmd.project_accounting_be.modules.project.repositories.ProjectRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectServices {

    private final ProjectRepository projectRepository;

    public Page<Project> getProjectList(GetProjectDTO param) {
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize());

        return projectRepository.findAll(pageable);
    }

    public void deleteByUuid(String uuid) {
        Optional<Project> existing = projectRepository.findById(uuid);
        if (existing.isPresent()) {
            projectRepository.delete(existing.get());
        }
    }

    public Project updateByUuid(String uuid, UpdateProjectDTO body) {
        Optional<Project> existing = projectRepository.findById(uuid);
        if (existing.isPresent()) {
            Project existingData = existing.get();
            existingData.setName(body.getName());
            existingData.setAddress(body.getAddress());
            existingData.setStartDate(body.getStartDate());
            existingData.setEndDate(body.getEndDate());
            existingData.setNotes(body.getNotes());

            projectRepository.save(existingData);

            return existingData;
        } else {
            return null;
        }
    }

    public Project insert(UpdateProjectDTO body) {
        Project toInsert = Project.builder()
                .name(body.getName())
                .address(body.getAddress())
                .startDate(body.getStartDate())
                .endDate(body.getEndDate())
                .notes(body.getNotes())
                .build();
        return projectRepository.save(toInsert);
    }
}
