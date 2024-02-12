package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.request.BranchRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.BranchResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Branch;
import com.seyhavorn.springbootecommerce.shop.repository.BranchRepository;
import com.seyhavorn.springbootecommerce.shop.service.BranchService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@Transactional
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;

    @Override
    public BranchResourceDto create(BranchRequestDto branchRequestDto) {
        return null;
    }

    @Override
    public Page<BranchResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        return null;
    }

    @Override
    public Branch update(BranchRequestDto branchRequestDto, Long id) {
        return null;
    }

    @Override
    public BranchResourceDto findById(Long id) {
        return null;
    }

    @Override
    public void deleteBranch(Long id) {

    }
}
