package com.gmd.project_accounting_be.modules.project.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gmd.project_accounting_be.modules.project.dtos.request.GetProjectDTO;
import com.gmd.project_accounting_be.modules.project.dtos.request.UpsertProjectDTO;
import com.gmd.project_accounting_be.modules.project.entities.Project;
import com.gmd.project_accounting_be.modules.project.repositories.ProjectRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

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

    public Project getByUuid(String uuid) {
        Optional<Project> optionalProject = projectRepository.findById(uuid);
        if (optionalProject.isPresent()) {
            return optionalProject.get();
        } else {
            return null;
        }
    }

    public Project updateByUuid(String uuid, UpsertProjectDTO body) {
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

    public Project insert(UpsertProjectDTO body) {
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
