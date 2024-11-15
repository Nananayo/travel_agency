package com.lvxing.travel_agency.filter;


import com.alibaba.fastjson.JSON;
import com.lvxing.travel_agency.common.BaseContext;
import com.lvxing.travel_agency.common.R;

import lombok.extern.slf4j.Slf4j;
import org.springframework.util.AntPathMatcher;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "loginCheckFilter",urlPatterns = "/*")
@Slf4j
public class LoginCheckFilter implements Filter {
    //路径匹配器,支持通配符
    public static final AntPathMatcher PATTERN_MATCHER = new AntPathMatcher();
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String requestURI = request.getRequestURI();
        log.info("拦截到请求：{}",requestURI);
        String[] urls = new String[]{
                "/employee/login",
                "/employee/logout",
                "/backend/**",
                "/front/**",
                "/user/sendMsg",
                "/user/login",
                "/doc.html",
                "/webjars/**",
                "/swagger-resources",
                "/v2/api-docs"
        };
        String[] RESTRICTED_URLS = new String[]{
                "/attractions/page",
                "/attractions/list",
                "/common/download",
                "/branchstore/page",
                "/branchstore/list",
                "/orders/**",
                "/route/page",
                "/route/list",
                "/user/sendMsg",
                "/user/login",
        };
        //判断当前路径是否包含在白名单中
        boolean check = check(urls,requestURI);
        if (check){
            log.info("本次请求{}不需要处理",requestURI);
            filterChain.doFilter(request,response);
            return;
        }
        //4-1判断登录状态是否登录
        if (request.getSession().getAttribute("employee")!=null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("employee"));
            Long empId = (Long) request.getSession().getAttribute("employee");
            BaseContext.setCurrentId(empId);
            filterChain.doFilter(request,response);
            return;
        }
        //4-2判断登录状态是否登录
        if (request.getSession().getAttribute("user")!=null){
            log.info("用户已登录，用户id为：{}",request.getSession().getAttribute("user"));
            Long userId = (Long) request.getSession().getAttribute("user");
            // 检查用户是否有权限访问请求的路径
            if (isRestricted(RESTRICTED_URLS,requestURI)) {
                log.info("访问成功：{}", requestURI);
                BaseContext.setCurrentId(userId);
                filterChain.doFilter(request,response);
                return;
            }
            response.getWriter().write(JSON.toJSONString(R.error("USER_NO_POWER")));
            log.info("用户无权限访问");
            return;
        }
        log.info("用户未登录");
        //在conllector控制层中加了@RestController注解会自动将方法的返回值转为json响应回去但是在过滤器中需要手动转换将对象转为json格式再响应回去
        response.getWriter().write(JSON.toJSONString(R.error("NOTLOGIN")));

        log.info("拦截到请求：{}",request.getRequestURI());
    }
    public boolean check(String[] urls,String requestURI){
        for (String url : urls) {
            boolean match = PATTERN_MATCHER.match(url,requestURI);
            if (match){
                return true;
            }
        }
        return false;
    }
    public boolean isRestricted(String[] RESTRICTED_URLS,String requestURI) {
        for (String restrictedUrl : RESTRICTED_URLS) {
            boolean match = PATTERN_MATCHER.match(restrictedUrl, requestURI);
            if (match) {
                return true;
            }
        }
        return false;
    }
}
