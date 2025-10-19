package com.gmd.project_accounting_be.modules.project.controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.gmd.project_accounting_be.core.constants.BaseErrorMessages;
import com.gmd.project_accounting_be.modules.project.constants.ProjectErrorMessages;
import com.gmd.project_accounting_be.modules.project.dtos.request.GetProjectRequestDTO;
import com.gmd.project_accounting_be.modules.project.dtos.request.UpsertProjectRequestDTO;
import com.gmd.project_accounting_be.modules.project.entities.Project;
import com.gmd.project_accounting_be.modules.project.services.ProjectService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/project")
@RequiredArgsConstructor
public class ProjectController {

    private final ProjectService projectService;

    @GetMapping("/list")
    public Page<Project> getProjectList(
        @RequestParam(required = false) String name, 
        @RequestParam(required = false) String address,
        @RequestParam(required = false) LocalDate startDate,
        @RequestParam(required = false) LocalDate endDate,
        @RequestParam(required = false) Integer page,
        @RequestParam(required = false) Integer size
    ) {
        try {
            GetProjectRequestDTO param = GetProjectRequestDTO.builder()
                .name(name != null ? name : "")
                .address(address != null ? address : "")
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .size(size)
                .build();

            return projectService.getProjectList(param);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, BaseErrorMessages.GENERAL_ERROR);
        }
    }

    @GetMapping("/detail/{uuid}")
    public Project getProjectDetail(@PathVariable String uuid) {
        try {
            return projectService.getByUuid(uuid);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, ProjectErrorMessages.NOT_FOUND);
        }
    }
    

    @PostMapping("/delete/{uuid}")
    public void deleteByUuid(@PathVariable String uuid) {
        try {
            projectService.deleteByUuid(uuid);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ProjectErrorMessages.ERROR_DELETE);
        }
    }
    
    @PostMapping("/update/{uuid}")
    public Project updateByUuid(@PathVariable String uuid, @Valid @RequestBody UpsertProjectRequestDTO body) {
        try {
            return projectService.updateByUuid(uuid, body);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ProjectErrorMessages.ERROR_UPDATE);
        }
    }
    
    @PostMapping("/insert")
    public Project insertProject(@Valid @RequestBody UpsertProjectRequestDTO body) {
        try {
            return projectService.insert(body);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, ProjectErrorMessages.ERROR_INSERT);
        }
    }
}