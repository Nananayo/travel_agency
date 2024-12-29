package com.lvxing.travel_agency.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lvxing.travel_agency.common.R;
import com.lvxing.travel_agency.entity.AttractionRoute;
import com.lvxing.travel_agency.entity.Employee;
import com.lvxing.travel_agency.entity.EmployeeAll;
import com.lvxing.travel_agency.service.IEmployeeAllService;
import com.lvxing.travel_agency.service.IEmployeeService;
import com.lvxing.travel_agency.service.Impl.EmployeeAllServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author author
 * @since 2024-11-28
 */
@RestController
@Slf4j
@Api(tags = "总操作员接口")
@RequestMapping("/employee-all")
public class EmployeeAllController {
    @Autowired
    private IEmployeeAllService employeeAllService;
    @PostMapping("/login")
    @ApiOperation("员工登入")
    public R<String> login(HttpServletRequest request, HttpSession session, @RequestBody EmployeeAll employee) {

        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        LambdaQueryWrapper<EmployeeAll> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmployeeAll::getUsername, employee.getUsername());
        employee = employeeAllService.getOne(queryWrapper);
        if(employee == null){
            return R.error("用户名不存在");
        }
        if(!employee.getPassword().equals(password)){
            return R.error("密码错误");
        }
        if(employee.getStatus() ==0){
            return R.error("账号已禁用");
        }
        request.getSession().setAttribute("employeeall", employee.getId());
//            Map<String , Object> claims = new HashMap<>();
//            claims.put("id", employee.getId());
//            claims.put("username",employee.getUsername());
//            claims.put("name",employee.getName());

        //使用JWT工具类，生成身份令牌
//            String token = JwtUtils.generateJwt(claims);
//            return R.success("aa");


//        log.info("登录成功...");
//        ServletContext sc = request.getServletContext();
//        sc.setAttribute("employee", employee.getId());
//
        
        return R.success(session.getId());


//        HashMap<String, String> plMap = new HashMap<>();
//        plMap.put("username", employee.getUsername());
//        String token = JwtUtil.getToken(plMap);
//        return R.success(token);

    }
    @PostMapping("/logout")
    @ApiOperation("员工登出")
    public R<String> logout(HttpServletRequest request){
        request.getSession().removeAttribute("employee");
        return R.success("退出成功");
    }
    @PostMapping
    @ApiOperation("新增员工")
    public R<String> save(@RequestBody EmployeeAll employee){
        if (employee.getId() != null){
            R.error("不许填ID");
        }
        if (employee.getPassword() == null){
            R.error("没填密码");
        }
        if (employee.getName() == null){
            R.error("没填员工名");
        }
        log.info("新增员工成功，员工信息为{}",employee.toString());
//        employee.setCreateTime(LocalDateTime.now());
//        employee.setUpdateTime(LocalDateTime.now());
        employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
        employeeAllService.save(employee);
        return R.success("新增员工成功");
    }
    @GetMapping("/page")
    @ApiOperation("分页查询员工信息")
    public R<Page> page(Page page, String name){
        if (name == null){
            Page<EmployeeAll> pageInfo = new Page(page.getCurrent(),page.getSize());
            LambdaQueryWrapper<EmployeeAll> queryWrapper = new LambdaQueryWrapper<>();
            employeeAllService.page(pageInfo,queryWrapper);
            return R.success(pageInfo);
        }

        if(page == null ){
            return R.error("参数错误");
        }
        Page<EmployeeAll> pageInfo = new Page(page.getCurrent(),page.getSize());
        LambdaQueryWrapper<EmployeeAll> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(EmployeeAll::getName,name);
        employeeAllService.page(pageInfo,queryWrapper);

        log.info("pageInfo:{}",pageInfo);
        return R.success(pageInfo);
    }
    @PutMapping
    @ApiOperation("更新员工信息")
    public R<String> update(HttpServletRequest request,@RequestBody EmployeeAll employee){
        log.info("员工信息为{}",employee.toString());
//        Long empId = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateTime(LocalDateTime.now());
//        employee.setUpdateUser(empId);
        employeeAllService.updateById(employee);
        return R.success("员工信息修改成功");
    }
    @GetMapping("/{id}")
    @ApiOperation("根据id查询员工信息")
    public R<EmployeeAll> getById(@PathVariable Long id){
        EmployeeAll employee = employeeAllService.getById(id);
        if (employee != null){
            return R.success(employee);
        }
        return R.error("没有查询到对应员工信息");

    }
    @DeleteMapping
    @ApiOperation("删除总店操作员")
    public R<String> delete(long ids){
        LambdaQueryWrapper<EmployeeAll> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(EmployeeAll::getId,ids);
        List<EmployeeAll> list = employeeAllService.list(queryWrapper);
        if (list == null){
            return R.error("删除失败");
        }
        employeeAllService.removeById(ids);
        return R.success("删除成功");
    }


}
