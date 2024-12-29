package com.lvxing.travel_agency.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lvxing.travel_agency.dto.BranchDto;
import com.lvxing.travel_agency.entity.BranchRoute;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.entity.Route;
import com.lvxing.travel_agency.mapper.BranchstoreMapper;
import com.lvxing.travel_agency.service.IBranchRouteService;
import com.lvxing.travel_agency.service.IBranchstoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lvxing.travel_agency.service.IRouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
@Service
public class BranchstoreServiceImpl extends ServiceImpl<BranchstoreMapper, Branchstore> implements IBranchstoreService {
    @Autowired
    private IRouteService routeService;
    @Autowired
    private IBranchRouteService branchRouteService;
    @Autowired
    private IBranchstoreService branchService;

    @Override
    @Transactional
    public void saveWithRoute(BranchDto branchDto) {
        this.save(branchDto);
        Long branchId = branchDto.getId();
        branchDto.getRoutes().stream().forEach(item ->{
            BranchRoute branchRoute = new BranchRoute();
            branchRoute.setBranchId(branchId);
            branchRoute.setBranchName(branchDto.getName());
            branchRoute.setRouteId(item.getId());
            branchRoute.setRouteName(item.getName());
            branchRouteService.save(branchRoute);
        });
    }

    @Override
    @Transactional
    public void updateWithRoute(BranchDto branchDto) {
        this.updateById(branchDto);
        Long branchId = branchDto.getId();
        LambdaQueryWrapper<BranchRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BranchRoute::getBranchId,branchId);
        branchRouteService.remove(queryWrapper);
        branchDto.getRoutes().stream().forEach(item ->{
            BranchRoute branchRoute = new BranchRoute();
            branchRoute.setBranchId(branchId);
            branchRoute.setBranchName(branchDto.getName());
            branchRoute.setRouteId(item.getId());
            branchRoute.setRouteName(item.getName());
            branchRouteService.save(branchRoute);
        });

    }

    @Override
    @Transactional
    public BranchDto getBranchWithRoute(Long id) {
        BranchDto branchDto = new BranchDto();
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Branchstore::getId,id);
        Branchstore branchstore = this.getOne(queryWrapper);
        LambdaQueryWrapper<BranchRoute> queryWrapper1 = new LambdaQueryWrapper<>();
        queryWrapper1.eq(BranchRoute::getBranchId,id);
        List<BranchRoute> list = branchRouteService.list(queryWrapper1);
        list.stream().forEach(item->{
            LambdaQueryWrapper<Route> queryWrapper2 = new LambdaQueryWrapper<>();
            queryWrapper2.eq(Route::getId,item.getRouteId());
            branchDto.getRoutes().add(routeService.getOne(queryWrapper2));
        });
        return branchDto;
    }

    @Override
    @Transactional
    public void removeWithRoute(BranchDto branchDto) {
        this.removeById(branchDto);
        System.out.println("branchDtoId = " + branchDto.getId());
        LambdaQueryWrapper<BranchRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BranchRoute::getBranchId,branchDto.getId());
        branchRouteService.remove(queryWrapper);
    }
}
