package com.lvxing.travel_agency.service.Impl;

import com.lvxing.travel_agency.entity.Employee;
import com.lvxing.travel_agency.mapper.EmployeeMapper;
import com.lvxing.travel_agency.service.IEmployeeService;
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
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
