package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.dto.RouteDto;
import com.lvxing.travel_agency.entity.Route;
import com.lvxing.travel_agency.service.IAttractionRouteService;
import com.lvxing.travel_agency.service.IAttractionsService;
import com.lvxing.travel_agency.service.IRouteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
@RestController
@RequestMapping("/route")
@Slf4j
public class RouteController {
    @Autowired
    private IRouteService routeService;
    @Autowired
    private IAttractionsService attractionsService;
    @PostMapping("/save")
    public R<RouteDto> save(@RequestBody RouteDto routeDto){
        routeService.saveWithAttractions(routeDto);
        log.info("新增成功");
        return R.success(routeDto);
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        log.info("page = {},pageSize = {},name = {}",page,pageSize,name);
        Page<Route> pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Route::getName,name);
        routeService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/list")
    public R<List<Route>> list(){
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getStatus,1);
        List<Route> list = routeService.list(queryWrapper);
        return R.success(list);
    }
    @GetMapping("/{id}")
    public R<RouteDto> get(@PathVariable Long id){
        RouteDto routeDto = routeService.getRouteWithAttractions(id);
        return R.success(routeDto);
    }
    @PutMapping("/update")
    public R<String> update(@RequestBody RouteDto routeDto){
        routeService.updateWithAttractions(routeDto);
        return R.success("修改成功");
    }
    @DeleteMapping
    public R<String> delete(RouteDto routeDto){
        routeService.removeWithAttractions(routeDto);
        return R.success("删除成功");
    }

}
