package com.lvxing.travel_agency.service.Impl;

import com.lvxing.travel_agency.dto.RouteDto;
import com.lvxing.travel_agency.dto.UserDto;
import com.lvxing.travel_agency.entity.User;
import com.lvxing.travel_agency.mapper.UserMapper;
import com.lvxing.travel_agency.service.IUserService;
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
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    @Override
    public void saveWithOrders(UserDto userDto) {

    }

    @Override
    public void updateWithOrders(UserDto userDto) {

    }

    @Override
    public void removeWithOrders(UserDto userDto) {

    }

    @Override
    public RouteDto getRouteWithOrders(Long id) {
        return null;
    }
}
