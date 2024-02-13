package com.seyhavorn.springbootecommerce.shop.mapper;

import com.seyhavorn.springbootecommerce.shop.dto.request.BranchRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.BranchResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Branch;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
public class BranchMapper {
    public BranchResourceDto branchToShopResourceDto(Branch branch) {
        BranchResourceDto branchResourceDto = new BranchResourceDto();
        BeanUtils.copyProperties(branch, branchResourceDto);
        return branchResourceDto;
    }

    public Branch branchRequestDtoToBranch(BranchRequestDto branchRequestDto) {
        Branch branch = new Branch();
        BeanUtils.copyProperties(branchRequestDto, branch);
        return branch;
    }
}
