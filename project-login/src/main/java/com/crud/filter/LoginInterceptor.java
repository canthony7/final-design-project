package com.crud.filter;

import com.crud.pojo.User;
import com.crud.service.UserService;
import com.crud.utils.CookieUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Resource
    UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("开始拦截.................");
        Cookie cookie = CookieUtils.getCookie(request, "userTicket");
        System.out.println(cookie);
        if (cookie == null){
            return false;
        }
        System.out.println(cookie.getValue());
        User user = userService.findUserByCookie(request);
        System.out.println(user);
        if (user == null){
            return false;
        }
        return true;
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
