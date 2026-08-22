package com.gmd.project_accounting_be.modules.store.services;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.gmd.project_accounting_be.core.utils.CommonUtil;
import com.gmd.project_accounting_be.modules.store.dto.request.GetStoreRequestDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.projections.StoreTagDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.StoreListRecordResponseDTO;
import com.gmd.project_accounting_be.modules.store.dto.response.projections.StoreListRecordSimpleDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.gmd.project_accounting_be.modules.store.repositories.StoreRepository;

import lombok.RequiredArgsConstructor;

import java.util.stream.Collectors;
import org.springframework.data.domain.PageImpl;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;

    public List<String> getAllStoreNames() {
        try {
            return storeRepository.getAllStoreNames();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    public Page<StoreListRecordResponseDTO> getAllStoreListRecord(GetStoreRequestDTO param) {
        // prepare sort and filter
        Sort sort = CommonUtil.generateSort(param.getSort());
        Pageable pageable = PageRequest.of(param.getPage(), param.getSize(), sort);

        // fetch store list
        Page<StoreListRecordSimpleDTO> storeList;
        if (param.getTagList() != null && param.getTagList().size() > 0) {
            storeList = storeRepository.findAllStoreListRecordWithTagFilter(param, pageable);
        } else {
            storeList = storeRepository.findAllStoreListRecord(param, pageable);
        }
        // fetch tag list of retrieved stores
        List<String> storeUuidList = storeList.getContent().stream().map(StoreListRecordSimpleDTO::getUuid).toList();
        List<StoreTagDTO> storeTagList = storeRepository.getStoreTagList(storeUuidList);
        Map<String, List<String>> storeTagMap = storeTagList.stream()
                .collect(Collectors.groupingBy(
                        StoreTagDTO::getStoreUuid,
                        Collectors.mapping(StoreTagDTO::getTag, Collectors.toList())));

        // construct list response dto (assigns tags to stores)
        List<StoreListRecordResponseDTO> responseList = storeList.getContent().stream()
                .map(store -> StoreListRecordResponseDTO.builder()
                        .uuid(store.getUuid())
                        .name(store.getName())
                        .address(store.getAddress())
                        .tags(storeTagMap.getOrDefault(store.getUuid(), List.of()))
                        .build())
                .toList();
        Page<StoreListRecordResponseDTO> responsePage = new PageImpl<>(
                responseList,
                storeList.getPageable(),
                storeList.getTotalElements());

        return responsePage;
    }
}
