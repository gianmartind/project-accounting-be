package com.gmd.project_accounting_be.modules.project.services;

import java.util.List;
import java.util.Optional;

import com.gmd.project_accounting_be.modules.purchase.entities.Purchase;
import com.gmd.project_accounting_be.modules.purchase.repositories.PurchaseRepository;
import com.gmd.project_accounting_be.modules.purchase_item.repositories.PurchaseItemRepository;
import org.springframework.stereotype.Service;

import com.gmd.project_accounting_be.core.utils.CommonUtil;
import com.gmd.project_accounting_be.modules.project.dtos.request.GetProjectRequestDTO;
import com.gmd.project_accounting_be.modules.project.dtos.request.UpsertProjectRequestDTO;
import com.gmd.project_accounting_be.modules.project.dtos.response.projections.ProjectListRecordResponse;
import com.gmd.project_accounting_be.modules.project.entities.Project;
import com.gmd.project_accounting_be.modules.project.repositories.ProjectRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final PurchaseRepository purchaseRepository;
    private final PurchaseItemRepository purchaseItemRepository;

    public Page<ProjectListRecordResponse> getProjectList(GetProjectRequestDTO param) {
        Sort sort = CommonUtil.generateSort(param.getSort());
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);
        return projectRepository.findAllProjectListRecord(param, pageable);
    }

    public void deleteByUuid(String uuid) {
        Optional<Project> existing = projectRepository.findById(uuid);
        existing.ifPresent(projectRepository::delete);
        List<Purchase> linkedPurchases = purchaseRepository.findAllByProjectUuid(uuid);
        List<String> purchaseUuids = linkedPurchases.stream()
                .map(Purchase::getUuid)
                .toList();
        purchaseItemRepository.deleteByPurchaseUuidIn(purchaseUuids);
        purchaseRepository.deleteAll(linkedPurchases);
    }

    public Project getByUuid(String uuid) {
        Optional<Project> optionalProject = projectRepository.findById(uuid);
        return optionalProject.orElse(null);
    }

    public Project updateByUuid(String uuid, UpsertProjectRequestDTO body) {
        Optional<Project> existing = projectRepository.findById(uuid);
        if (existing.isPresent()) {
            Project existingData = existing.get();
            existingData.setName(body.getName());
            existingData.setOwner(body.getOwner());
            existingData.setCity(body.getCity());
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

    public Project insert(UpsertProjectRequestDTO body) {
        Project toInsert = Project.builder()
                .name(body.getName())
                .owner(body.getOwner())
                .city(body.getCity())
                .address(body.getAddress())
                .startDate(body.getStartDate())
                .endDate(body.getEndDate())
                .notes(body.getNotes())
                .build();
        return projectRepository.save(toInsert);
    }
}
