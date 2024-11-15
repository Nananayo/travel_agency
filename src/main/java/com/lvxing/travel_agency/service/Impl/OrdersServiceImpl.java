package com.lvxing.travel_agency.service.Impl;

import com.lvxing.travel_agency.entity.Orders;
import com.lvxing.travel_agency.mapper.OrdersMapper;
import com.lvxing.travel_agency.service.IOrdersService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author author
 * @since 2024-10-08   
 */
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders> implements IOrdersService {

}
