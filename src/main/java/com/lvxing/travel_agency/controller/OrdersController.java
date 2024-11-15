package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.lvxing.travel_agency.common.BaseContext;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.entity.Orders;
import com.lvxing.travel_agency.entity.User;
import com.lvxing.travel_agency.service.IOrdersService;
import com.lvxing.travel_agency.service.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
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
@RequestMapping("/orders")
@Slf4j
@Api(tags = "订单接口")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;
    @Autowired
    private IUserService userService;

    @GetMapping("/{id}")
    @ApiOperation("根据id查询订单")
    public R<Orders> getById(@PathVariable Long id) {
        ordersService.getById(id);
        return R.success(ordersService.getById(id));
    }
    @PostMapping
    @ApiOperation("新增订单")
    public R<String> save(@RequestBody Orders orders) {
        if (orders.getRouteId() == null){
            return R.error("请选择旅游线路");
        }
        List<Integer> list = new ArrayList<>();
        Collections.addAll(list,1,2,3,4);
        if (list.contains(orders.getStatus())&&orders.getStatus() != null){
            orders.setUserId(userService.getById(BaseContext.getCurrentId()).getId());
            ordersService.save(orders);
            orders.setOrderTime(LocalDateTime.now());
            log.info("新增订单{}",orders);
            return R.success("新增成功");
        }
        return R.error("参数错误");
    }
    @PutMapping("/update")
    @ApiOperation("更新订单")
    public R<String> update(@RequestBody Orders orders) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Orders::getUserId,BaseContext.getCurrentId());
        Orders orders1 = ordersService.getOne(queryWrapper);
        orders1.setStatus(orders.getStatus());
        if (orders.getStatus() ==2){
            orders1.setCheckoutTime(LocalDateTime.now());
        }
        ordersService.updateById(orders1);
        return R.success("修改成功");
    }
    @GetMapping("/list")
    @ApiOperation("获取订单列表")
    public R<List<Orders>> list(Orders orders) {
        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(orders.getUserId() != null,Orders::getUserId,BaseContext.getCurrentId());
        return R.success(ordersService.list(queryWrapper));
    }
}
