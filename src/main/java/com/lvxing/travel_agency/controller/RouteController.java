package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.dto.RouteDto;
import com.lvxing.travel_agency.entity.BranchRoute;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.entity.Employee;
import com.lvxing.travel_agency.entity.Route;
import com.lvxing.travel_agency.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "路线接口")
public class RouteController {
    @Autowired
    private IRouteService routeService;
    @Autowired
    private IAttractionsService attractionsService;
    @Autowired
    private IBranchstoreService branchService;
    @Autowired
    private IBranchRouteService branchRouteService;
    @PostMapping("/save")
    @ApiOperation("新增路线")
    public R<RouteDto> save(@RequestBody RouteDto routeDto){
        routeService.saveWithAttractions(routeDto);
        log.info("新增成功");
        return R.success(routeDto);
    }
    @GetMapping("/page")
    @ApiOperation("分页查询路线")
    public R<Page> page(Page page,String name){
        if (name == null){
            Page<Route> pageInfo = new Page(page.getCurrent(),page.getSize());
            LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
            routeService.page(pageInfo,queryWrapper);
            return R.success(pageInfo);
        }

        if(page == null ){
            return R.error("参数错误");
        }
        Page<Route> pageInfo = new Page(page.getCurrent(),page.getSize());
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Route::getName,name);
        routeService.page(pageInfo,queryWrapper);

        log.info("pageInfo:{}",pageInfo);
        return R.success(pageInfo);
    }
    @GetMapping("/pagebranch")
    @ApiOperation("分页查询路线通过分店")
    public R<Page> pagebranch(Page page,String branchName,String name){
        if (name == null){
            Page<BranchRoute> pageInfo = new Page(page.getCurrent(),page.getSize());
            LambdaQueryWrapper<BranchRoute> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(BranchRoute::getBranchName,branchName);
            branchRouteService.page(pageInfo,queryWrapper);
            return R.success(pageInfo);
        }

        if(page == null ){
            return R.error("参数错误");
        }
        Page<BranchRoute> pageInfo = new Page(page.getCurrent(),page.getSize());
        LambdaQueryWrapper<BranchRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(BranchRoute::getBranchName,branchName);
        queryWrapper.like(BranchRoute::getRouteName,name);
        branchRouteService.page(pageInfo,queryWrapper);
        return R.success(pageInfo);
    }
    @GetMapping("/branchroute")
    @ApiOperation("新增分店路线")
    public R<String> setbranchroute(BranchRoute branchRoute){
        if (branchRoute.getBranchId() == null){
            return R.error("没有分店id");
        }
        if (branchRoute.getRouteId() == null){
            return R.error("没有路线id");
        }
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Branchstore::getId,branchRoute.getBranchId());
        Branchstore branchstore = branchService.getOne(queryWrapper);
        LambdaQueryWrapper<Route> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.eq(Route::getId, branchRoute.getRouteId());
        Branchstore branchroute = branchService.getOne(queryWrapper);
        branchRoute.setBranchName(branchstore.getName());
        branchRoute.setRouteName(branchroute.getName());
        branchRouteService.save(branchRoute);
        return R.success("新增成功");
    }
    @DeleteMapping("/deleteBranchRoute")
    @ApiOperation("删除分店路线")
    public R<String> deleteBranchRoute(BranchRoute branchRoute){
        if (branchRoute.getId() == null){
            return R.error("参数错误");
        }
        branchRouteService.removeById(branchRoute.getId());
        return R.success("新增成功");
    }
    @GetMapping("/list")
    @ApiOperation("获取路线列表")
    public R<List<Route>> list(){
        LambdaQueryWrapper<Route> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Route::getStatus,1);
        List<Route> list = routeService.list(queryWrapper);
        return R.success(list);
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询路线")
    public R<RouteDto> get(@PathVariable Long id){
        RouteDto routeDto = routeService.getRouteWithAttractions(id);
        return R.success(routeDto);
    }
    @PutMapping("/update")
    @ApiOperation("更新路线")
    public R<String> update(@RequestBody RouteDto routeDto){
        routeService.updateWithAttractions(routeDto);
        return R.success("修改成功");
    }
//    @PutMapping("/update")
//    @ApiOperation("更新路线")
//    public R<String> update(@RequestBody RouteDto routeDto){
//        routeService.updateWithAttractions(routeDto);
//        return R.success("修改成功");
//    }
    @DeleteMapping
    @ApiOperation("删除路线")
    public R<String> delete(RouteDto routeDto){
        routeService.removeWithAttractions(routeDto);
        return R.success("删除成功");
    }


}
