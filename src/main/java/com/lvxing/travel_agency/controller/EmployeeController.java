package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.entity.Branchstore;
import com.lvxing.travel_agency.entity.Employee;
import com.lvxing.travel_agency.entity.EmployeeAll;
import com.lvxing.travel_agency.service.IEmployeeService;
//import com.lvxing.travel_agency.utils.JwtUtils;
import com.lvxing.travel_agency.utils.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-10-08
 */
@RestController
@RequestMapping("/employee")
@Slf4j
@Api(tags = "员工接口")
public class EmployeeController {
    @Autowired
    private IEmployeeService employeeService;
    @PostMapping("/login")
    @ApiOperation("员工登入")
    public R<String> login(HttpServletRequest request, HttpSession session, @RequestBody Employee employee) {

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        employee = employeeService.getOne(queryWrapper);
        if(employee == null){
            return R.error("用户名不存在");
        }
        if(!employee.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if(employee.getStatus() ==0){
            return R.error("账号已禁用");
        }

        request.getSession().setAttribute("employee", employee.getId());
        return R.success(session.getId());

    }
    @PostMapping("/logout")
    @ApiOperation("员工登出")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    @PostMapping
    @ApiOperation("新增员工")
    public R<String> save(@RequestBody Employee employee){
        if (employee.getId() != null){
            R.error("不许填ID");
        }
        if (employee.getPassword() == null){
            R.error("没填密码");
        }
        if (employee.getName() == null){
            R.error("没填员工名");
        }
        if (employee.getUsername() == null){
            R.error("没填用户名");
        }
        if (employee.getUsername() == null){
            R.error("没填用户名");
        }
        log.info("新增员工成功，员工信息为{}",employee.toString());
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeService.save(employee);
        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    @ApiOperation("分页查询员工信息")
    public R<Page> page(Page page,String name){
        if (name == null){
            Page<Employee> pageInfo = new Page(page.getCurrent(),page.getSize());
            LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
            employeeService.page(pageInfo,queryWrapper);
            return R.success(pageInfo);
        }

        if(page == null ){
            return R.error("参数错误");
        }
        Page<Employee> pageInfo = new Page(page.getCurrent(),page.getSize());
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Employee::getName,name);
        employeeService.page(pageInfo,queryWrapper);

        log.info("pageInfo:{}",pageInfo);
        return R.success(pageInfo);
    }
    @PutMapping
    @ApiOperation("更新员工信息")
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){
        if (employee.getName() == null){
            return R.error("员工名不能为空");
        }
        if (employee.getId()==null){
            return R.error("id不能为空");
        }
        if (employee.getPhone()==null){
            return R.error("phone不能为空");
        }
        if (employee.getGender()==null){
            return R.error("性别不能为空");
        }
        log.info("员工信息为{}",employee.toString());
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeService.updateById(employee);
        return R.success("员工信息修改成功");
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工信息")
    public R<Employee> getById(@PathVariable Long id){
        Employee employee = employeeService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");
    }
    @DeleteMapping
    @ApiOperation("删除员工")
    public R<String> delete(long id){
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getId,id);
        List<Employee> list = employeeService.list(queryWrapper);
        if (list == null){
            return R.error("删除失败");
        }
        employeeService.removeById(id);
        return R.success("删除成功");
    }

}
