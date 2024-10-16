package com.lvxing.travel_agency.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lvxing.travel_agency.entity.Attractions;
import com.lvxing.travel_agency.entity.BranchRoute;
import com.lvxing.travel_agency.entity.Route;
import com.lvxing.travel_agency.mapper.AttractionsMapper;
import com.lvxing.travel_agency.service.IAttractionsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
@Service
public class AttractionsServiceImpl extends ServiceImpl<AttractionsMapper, Attractions> implements IAttractionsService {
//    @Autowired
//    private BranchRoute branchRoute;
//    @Autowired
//    private Route route;
//    public void remove(long id) {
//        LambdaQueryWrapper<BranchRoute> queryWrapper1 = new LambdaQueryWrapper<>();
//        queryWrapper1.eq(BranchRoute::getId,id);
//
//
//        LambdaQueryWrapper<Route> queryWrapper2 = new LambdaQueryWrapper<>();
//        queryWrapper2.eq(Route::getId,id);
//
//    }
}
