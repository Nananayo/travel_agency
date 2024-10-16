package com.lvxing.travel_agency.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.lvxing.travel_agency.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper extends BaseMapper<User> {
}
