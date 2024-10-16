package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.entity.Attractions;
import com.lvxing.travel_agency.service.Impl.AttractionsServiceImpl;
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
@RequestMapping("/attractions")
@Slf4j
public class AttractionsController {
    @Autowired
    private AttractionsServiceImpl attractionsService;
    @GetMapping("/id")
    public R<Attractions> getById(long id){
        return R.success(attractionsService.getById(id));
    }
    @GetMapping("/page")
    public R<Page> getByPage(int page,int pageSize){
        Page<Attractions> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Attractions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByDesc(Attractions::getStatus);
        attractionsService.page(pageInfo,queryWrapper);
        log.info("okok");
        return R.success(pageInfo);
    }
    @GetMapping("/list")
    private R<List<Attractions>> list(Attractions attractions){
        LambdaQueryWrapper<Attractions> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(attractions.getStatus()!=null,Attractions::getId,attractions.getId());
        List<Attractions> list = attractionsService.list(queryWrapper);
        return R.success(list);
    }
    @PostMapping
    public R<Attractions> save(@RequestBody Attractions attractions){
        attractionsService.save(attractions);
        log.info(attractions.toString());
        return R.success(attractions);
    }
    @DeleteMapping
    public R<String> delete(long ids){
//        attractionsService.remove(ids);
        return R.success("删除成功");
    }
}
