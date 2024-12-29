package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.entity.AttractionRoute;
import com.lvxing.travel_agency.entity.Attractions;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.service.IAttractionRouteService;
import com.lvxing.travel_agency.service.IBranchstoreService;
import com.lvxing.travel_agency.service.IRouteService;
import com.lvxing.travel_agency.service.Impl.AttractionsServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
@RequestMapping("/attractions")
@Slf4j
@Api(tags = "景点接口")
public class AttractionsController {
    @Autowired
    private AttractionsServiceImpl attractionsService;
    @Autowired
    private IRouteService routeService;
    @Autowired
    private IAttractionRouteService attractionRouteService;
    @GetMapping("/id")
    @ApiOperation("根据id查询景点")
    public R<Attractions> getById(long id){
        return R.success(attractionsService.getById(id));
    }
    @GetMapping("/page")
    @ApiOperation("分页查询景点")
    public R<Page> getByPage(Page page,String name){
        if (name == null){
            Page<Attractions> pageInfo = new Page(page.getCurrent(),page.getSize());
            LambdaQueryWrapper<Attractions> queryWrapper = new LambdaQueryWrapper<>();
            attractionsService.page(pageInfo,queryWrapper);
            return R.success(pageInfo);
        }

        if(page == null ){
            return R.error("参数错误");
        }
        Page<Attractions> pageInfo = new Page(page.getCurrent(),page.getSize());
        LambdaQueryWrapper<Attractions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Attractions::getName,name);
        attractionsService.page(pageInfo,queryWrapper);

        log.info("pageInfo:{}",pageInfo);
        return R.success(pageInfo);
    }
    @GetMapping("/list")
    @ApiOperation("查询景点列表")
    private R<List<Attractions>> list(Attractions attractions){
        LambdaQueryWrapper<Attractions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(attractions.getStatus()!=null,Attractions::getId,attractions.getId());
        List<Attractions> list = attractionsService.list(queryWrapper);
        return R.success(list);
    }
    @PostMapping
    @ApiOperation("新增景点")
    public R<Attractions> save(@RequestBody Attractions attractions){
        if(attractions.getPhone() == null){
            return R.error("电话为空");
        }
        if(attractions.getName() == null){
            return R.error("名字为空为空");
        }
        attractionsService.save(attractions);
        log.info(attractions.toString());
        return R.success(attractions);
    }
    @DeleteMapping
    @ApiOperation("删除景点")
    public R<String> delete(long ids){
        LambdaQueryWrapper<AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttractionRoute::getAttractionsId,ids);
        List<AttractionRoute> list = attractionRouteService.list(queryWrapper);
        if (list == null){
            return R.error("路线包含该景点删除失败");
        }
        attractionsService.removeById(ids);
        return R.success("删除成功");
    }
    @DeleteMapping("/ids")
    @ApiOperation("批量删除景点")
    public R<String> delete(@RequestParam("ids") List<Long> ids){
        System.out.println(ids);
            LambdaQueryWrapper<AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.in(AttractionRoute::getAttractionsId, ids);
            List<AttractionRoute> list = attractionRouteService.list(queryWrapper);
        System.out.println(list);
            if (list.size() > 0){
                return R.error("路线包含该景点删除失败");
            }
//
        System.out.println(ids);

        if (ids == null){
            return R.error("ids为空");
        }
            for (Long id : ids) {
                LambdaQueryWrapper<Attractions> queryWrapper1 = new LambdaQueryWrapper<>();
                queryWrapper1.eq(Attractions::getId, id);
                attractionsService.remove(queryWrapper1);
                System.out.println(id);
            }

        return R.success("删除成功");
    }
    @PutMapping
    @ApiOperation("更新景点信息")
    public R<String> update(@RequestBody Attractions attractions){
        if(attractions.getId() == null){
            return R.error("id为空");
        }
        if(attractions.getPhone() == null){
            return R.error("电话为空");
        }
        if(attractions.getName() == null){
            return R.error("姓名为空");
        }

        LambdaQueryWrapper<AttractionRoute> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AttractionRoute::getAttractionsId, attractions.getId());
        List<AttractionRoute> list = attractionRouteService.list(queryWrapper);
        if (list == null){
            return R.error("路线包含该景点修改失败");
        }
        attractionsService.updateById(attractions);
        return R.success("修改成功");
    }
}
