package com.lvxing.travel_agency.service;

import com.lvxing.travel_agency.dto.BranchDto;
import com.lvxing.travel_agency.entity.AttractionRoute;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-10-15
 */
public interface IAttractionRouteService extends IService<AttractionRoute> {



    @Transactional
    void getAttractionRouteByAttraction(BranchDto branchDto);
}
