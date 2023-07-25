package com.crud.controller;

import com.crud.utils.CookieUtils;
import com.crud.vo.ResponseBean;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chet
 * @date 15/6/2023 10:43 pm
 * @description
 */
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class SystemController {

    @Resource
    Cache cache;

    @RequestMapping("/system")
    public String systemTest(){
        return "systemTest";
    }

    @RequestMapping("/check")
    public ResponseBean checkStatus(HttpServletRequest request){
        Cookie cookie = CookieUtils.getCookie(request, "userTicket");
        String ticket = cookie.getValue();
        Object value = cache.get("user" + ticket).getObjectValue();
        return ResponseBean.success(value, 1);
    }

}
