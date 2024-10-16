package com.lvxing.travel_agency.service;

import com.lvxing.travel_agency.dto.BranchDto;
import com.lvxing.travel_agency.entity.Branchstore;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
public interface IBranchstoreService extends IService<Branchstore> {
        public void saveWithRoute(BranchDto branchDto);
        public void updateWithRoute(BranchDto branchDto);
        public BranchDto getBranchWithRoute(Long id);
        public void removeWithRoute(BranchDto branchDto);
}
