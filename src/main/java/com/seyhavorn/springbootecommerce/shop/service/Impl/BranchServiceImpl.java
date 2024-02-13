package com.seyhavorn.springbootecommerce.shop.service.Impl;

import com.seyhavorn.springbootecommerce.authentication.dto.FilterRequestDto;
import com.seyhavorn.springbootecommerce.authentication.specifications.FilterSpecificationService;
import com.seyhavorn.springbootecommerce.shop.dto.request.BranchRequestDto;
import com.seyhavorn.springbootecommerce.shop.dto.resources.BranchResourceDto;
import com.seyhavorn.springbootecommerce.shop.entity.Branch;
import com.seyhavorn.springbootecommerce.shop.entity.Shop;
import com.seyhavorn.springbootecommerce.shop.mapper.BranchMapper;
import com.seyhavorn.springbootecommerce.shop.repository.BranchRepository;
import com.seyhavorn.springbootecommerce.shop.repository.ShopRepository;
import com.seyhavorn.springbootecommerce.shop.service.BranchService;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final ShopRepository shopRepository;
    private final BranchMapper branchMapper;
    private final FilterSpecificationService<Branch> filterSpecificationService;

    @Override
    @CacheEvict(value = "branches", allEntries = true)
    public BranchResourceDto create(BranchRequestDto branchRequestDto) {
        Shop shop = checkShop(branchRequestDto.getShop_id());
        Branch branch = branchMapper.branchRequestDtoToBranch(branchRequestDto);
        branch.setShop(shop);
        Branch b1 = branchRepository.save(branch);
        return branchMapper.branchToShopResourceDto(b1);
    }

    @Override
    @Cacheable(value = "branches")
    public Page<BranchResourceDto> findAll(int page, int size, FilterRequestDto filterRequestDto) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Branch> branches;

        if (filterRequestDto != null) {
            Specification<Branch> specification = filterSpecificationService.filterSpecification(
                    filterRequestDto.getSearchRequestDtoList(), filterRequestDto.getGlobalOperator()
            );

            branches = branchRepository.findAll(specification, pageRequest);
        } else {
            branches = branchRepository.findAll(pageRequest);
        }

        return new PageImpl<>(branches.getContent()
                .stream()
                .map(branchMapper::branchToShopResourceDto).toList(),
                pageRequest, branches.getTotalElements());
    }

    @Override
    @CacheEvict(value = "branches", key = "#id", allEntries = true)
    public BranchResourceDto update(BranchRequestDto branchRequestDto, Long id) {
        Branch branch = checkBranch(id);
        Shop shop = checkShop(branchRequestDto.getShop_id());
        BeanUtils.copyProperties(branchRequestDto, branch);
        branch.setShop(shop);
        branchRepository.save(branch);
        return branchMapper.branchToShopResourceDto(branch);
    }

    @Override
    @Cacheable(value = "branches", key = "#id")
    public BranchResourceDto findById(Long id) {
        Branch branch = checkBranch(id);
        return branchMapper.branchToShopResourceDto(branch);
    }

    @Override
    @CacheEvict(value = "branches", key = "#id")
    public void deleteBranch(Long id) {
        Branch branch = checkBranch(id);
        branchRepository.deleteById(branch.getId());
    }

    @Override
    @Cacheable(value = "branches")
    public List<BranchResourceDto> branchByShopId(Long shopId) {
        Shop shop = checkShop(shopId);
        List<Branch> branches = branchRepository.findBranchByShop(shop);
        return branches.stream().map(branchMapper::branchToShopResourceDto).toList();
    }

    private Branch checkBranch(Long branchId) {
        return branchRepository.findById(branchId).orElseThrow(() -> new RuntimeException("Branch not found!"));
    }

    private Shop checkShop(Long shopId) {
        return shopRepository.findById(shopId).orElseThrow(() -> new RuntimeException("Shop not found!"));
    }
}
