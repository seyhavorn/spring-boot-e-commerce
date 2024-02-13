package com.seyhavorn.springbootecommerce.shop.service;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.BranchRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.BranchResourceDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BranchService {
    BranchResourceDto create(BranchRequestDto branchRequestDto);

    Page<BranchResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto);

    BranchResourceDto update(BranchRequestDto branchRequestDto, Long id);

    BranchResourceDto findById(Long id);

    void deleteBranch(Long id);

    List<BranchResourceDto> branchByShopId(Long shopId);
}
