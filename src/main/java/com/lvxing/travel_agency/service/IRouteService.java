package com.lvxing.travel_agency.service;

import com.lvxing.travel_agency.dto.RouteDto;
import com.lvxing.travel_agency.entity.Route;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
public interface IRouteService extends IService<Route> {
    public void saveWithAttractions(RouteDto routeDto);
    public void updateWithAttractions(RouteDto routeDto);
    public void removeWithAttractions(RouteDto routeDto);
    public RouteDto getRouteWithAttractions(Long id);
}
