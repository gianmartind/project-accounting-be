package com.gmd.project_accounting_be.modules.project.controller;

import org.springframework.web.bind.annotation.RestController;

import com.gmd.project_accounting_be.modules.project.dtos.GetProjectDTO;
import com.gmd.project_accounting_be.modules.project.dtos.UpdateProjectDTO;
import com.gmd.project_accounting_be.modules.project.entities.Project;
import com.gmd.project_accounting_be.modules.project.services.ProjectServices;

import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

import org.springframework.data.domain.Page;
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

    private final ProjectServices projectServices;

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
            GetProjectDTO param = GetProjectDTO.builder()
                .name(name != null ? name : "")
                .address(address != null ? address : "")
                .startDate(startDate)
                .endDate(endDate)
                .page(page)
                .size(size)
                .build();

            return projectServices.getProjectList(param);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/delete/{uuid}")
    public void deleteByUuid(@PathVariable String uuid) {
        try {
            projectServices.deleteByUuid(uuid);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @PostMapping("/update/{uuid}")
    public Project updateByUuid(@PathVariable String uuid, @RequestBody UpdateProjectDTO body) {
        try {
            return projectServices.updateByUuid(uuid, body);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    
    @PostMapping("/insert")
    public Project insertProject(@RequestBody UpdateProjectDTO body) {
        try {
            return projectServices.insert(body);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}