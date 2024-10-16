package com.lvxing.travel_agency.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lvxing.travel_agency.dto.RouteDto;
import com.lvxing.travel_agency.entity.AttractionRoute;
import com.lvxing.travel_agency.entity.Attractions;
import com.lvxing.travel_agency.entity.Route;
import com.lvxing.travel_agency.mapper.RouteMapper;
import com.lvxing.travel_agency.service.IAttractionRouteService;
import com.lvxing.travel_agency.service.IAttractionsService;
import com.lvxing.travel_agency.service.IRouteService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
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
public class RouteServiceImpl extends ServiceImpl<RouteMapper, Route> implements IRouteService {
    @Autowired
    public IRouteService routeService;
    @Autowired
    public IAttractionsService attractionsService;
    @Autowired
    public IAttractionRouteService attractionRouteService;

    @Override
    @Transactional
    public void saveWithAttractions(RouteDto routeDto) {
        this.save(routeDto);
        Long routeId = routeDto.getId();
        LambdaQueryWrapper <AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttractionRoute::getRouteId,routeId);
        Route route = routeService.getById(routeId);
        routeDto.getAttractions().stream().forEach(item->{
            AttractionRoute attractionRoute = new AttractionRoute();
            attractionRoute.setAttractionName(item.getName());
            attractionRoute.setAttractionsId(item.getId());
            attractionRoute.setRouteId(routeDto.getId());
            attractionRoute.setRouteName(routeDto.getName());
            attractionRouteService.save(attractionRoute);
        });

    }

    @Override
    @Transactional
    public void updateWithAttractions(RouteDto routeDto) {
        this.updateById(routeDto);
        LambdaQueryWrapper<AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttractionRoute::getRouteId,routeDto.getId());
        attractionRouteService.remove(queryWrapper);
        routeDto.getAttractions().stream().forEach(item->{
            AttractionRoute attractionRoute = new AttractionRoute();
            attractionRoute.setAttractionName(item.getName());
            attractionRoute.setAttractionsId(item.getId());
            attractionRoute.setRouteId(routeDto.getId());
            attractionRoute.setRouteName(routeDto.getName());
            attractionRouteService.save(attractionRoute);
        });
    }

    @Override
    @Transactional
    public void removeWithAttractions(RouteDto routeDto) {
        this.removeById(routeDto);
        LambdaQueryWrapper<AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttractionRoute::getRouteId,routeDto.getId());
        attractionRouteService.remove(queryWrapper);

    }

    @Override
    @Transactional
    public RouteDto getRouteWithAttractions(Long id) {
        Route route = this.getById(id);
        RouteDto routeDto = new RouteDto();
        BeanUtils.copyProperties(route,routeDto);
        LambdaQueryWrapper<AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttractionRoute::getRouteId,id);
        List<AttractionRoute> attractions = attractionRouteService.list(queryWrapper);
        attractions.stream().forEach(item->{
            LambdaQueryWrapper<Attractions> queryWrapper1 = new LambdaQueryWrapper<>();
            queryWrapper1.eq(Attractions::getId,item.getAttractionsId());
            routeDto.getAttractions().add(attractionsService.getOne(queryWrapper1));
        });
        return routeDto;
    }
}
