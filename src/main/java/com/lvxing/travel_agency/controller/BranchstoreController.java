package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.dto.BranchDto;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.service.IBranchstoreService;
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
@RequestMapping("/branchstore")
@Slf4j
public class BranchstoreController {
    @Autowired
    private IBranchstoreService branchService;
    @Autowired
    private IRouteService routeService;
    @PostMapping("/save")
    public R<String> save(@RequestBody BranchDto branchDto)
    {
        branchService.saveWithRoute(branchDto);
        return R.success("添加成功");
    }
    @GetMapping("/page")
    public R<Page> page(int page, int pageSize, String name){
        Page<Branchstore> pageInfo = new Page(page,pageSize);
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(name != null,Branchstore::getName,name);
        branchService.page(pageInfo,queryWrapper);
        log.info("pageInfo:{}",pageInfo);
        return R.success(pageInfo);
    }
    @GetMapping("/list")
    public R<List<Branchstore>> list(Branchstore branchstore){
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(branchstore.getStatus()!=null,Branchstore::getStatus,branchstore.getStatus());
        List<Branchstore> list = branchService.list(queryWrapper);
        log.info("list:{}",list);
        return R.success(list);
    }
    @PutMapping("/update")
    public R<String> update(@RequestBody BranchDto branchDto){

        branchService.updateWithRoute(branchDto);
        return R.success("修改成功");
    };
    @GetMapping("/{id}")
    public R<BranchDto> get(@PathVariable Long id){
        BranchDto branchDto = branchService.getBranchWithRoute(id);
        return R.success(branchDto);
    }
    @DeleteMapping
    public R<String> delete(BranchDto branchDto){
        branchService.removeWithRoute(branchDto);
        return R.success("删除成功");
    }
}
