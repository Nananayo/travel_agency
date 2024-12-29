package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.dto.BranchDto;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.service.IBranchstoreService;
import com.lvxing.travel_agency.service.IRouteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
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
@Api(tags = "分店接口")
public class BranchstoreController {
    @Autowired
    private IBranchstoreService branchService;
    @Autowired
    private IRouteService routeService;
    @PostMapping("/save")
    @ApiOperation("新增分店")
    public R<String> save(@RequestBody BranchDto branchDto)
    {
        branchService.saveWithRoute(branchDto);
        return R.success("添加成功");
    }
    @GetMapping("/page")
    @ApiOperation("分页查询分店")
    public R<Page> page(Page page,String name){
        if (name == null){
            Page<Branchstore> pageInfo = new Page(page.getCurrent(),page.getSize());
            LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
            branchService.page(pageInfo,queryWrapper);
            return R.success(pageInfo);
        }

        if(page == null ){
            return R.error("参数错误");
        }
        Page<Branchstore> pageInfo = new Page(page.getCurrent(),page.getSize());
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Branchstore::getName,name);
        branchService.page(pageInfo,queryWrapper);

        log.info("pageInfo:{}",pageInfo);
        return R.success(pageInfo);
    }

    @GetMapping("/search")
    @ApiOperation("搜索分店")
    public R<List<Branchstore>> Search(String name){
        if(name == null ){
            return R.error("参数错误");
        }
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Branchstore::getName,name);
        List<Branchstore> list = branchService.list(queryWrapper);

        return R.success(list);
    }
    @GetMapping("/list")
    @ApiOperation("获取分店列表")
    public R<List<Branchstore>> list(Branchstore branchstore){
        LambdaQueryWrapper<Branchstore> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(branchstore.getStatus()!=null,Branchstore::getStatus,branchstore.getStatus());
        List<Branchstore> list = branchService.list(queryWrapper);
        log.info("list:{}",list);
        return R.success(list);
    }
    @PutMapping("/update")
    @ApiOperation("更新分店信息")
    public R<String> update(@RequestBody BranchDto branchDto){
        if(branchDto.getId() == null){
            return R.error("id为空");
        }
        branchService.updateWithRoute(branchDto);
        return R.success("修改成功");
    };
    @GetMapping("/{id}")
    @ApiOperation("根据ID查找分店")
    public R<BranchDto> get(@PathVariable Long id){
        BranchDto branchDto = branchService.getBranchWithRoute(id);
        return R.success(branchDto);
    }
    @DeleteMapping
    @ApiOperation("删除分店")
    public R<String> delete(BranchDto branchDto){
        branchService.removeWithRoute(branchDto);
        return R.success("删除成功");
    }
}
