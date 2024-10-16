package com.lvxing.travel_agency.service;

import com.lvxing.travel_agency.dto.RouteDto;
import com.lvxing.travel_agency.dto.UserDto;
import com.lvxing.travel_agency.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
public interface IUserService extends IService<User> {
    public void saveWithOrders(UserDto userDto);
    public void updateWithOrders(UserDto userDto);
    public void removeWithOrders(UserDto userDto);
    public RouteDto getRouteWithOrders(Long id);

}
