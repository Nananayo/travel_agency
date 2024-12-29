package com.lvxing.travel_agency.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvxing.travel_agency.dto.BranchDto;
import com.lvxing.travel_agency.entity.AttractionRoute;
import com.lvxing.travel_agency.mapper.AttractionRouteMapper;
import com.lvxing.travel_agency.service.IAttractionRouteService;
import com.lvxing.travel_agency.service.IBranchRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AttractionRouteServiceImpl extends ServiceImpl<AttractionRouteMapper, AttractionRoute> implements IAttractionRouteService {
    @Autowired
    private IBranchRouteService branchRouteService;
    @Override
    @Transactional
    public void getAttractionRouteByAttraction(BranchDto branchDto) {

    }
}