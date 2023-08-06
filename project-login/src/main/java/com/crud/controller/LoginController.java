package com.crud.controller;

import com.crud.exception.GlobalException;
import com.crud.pojo.User;
import com.crud.service.UserService;
import com.crud.utils.CookieUtils;
import com.crud.utils.UUIDUtils;
import com.crud.vo.LoginVo;
import com.crud.vo.ResponseBean;
import com.crud.vo.ResponseEnum;
import com.crud.vo.UserHolder;
import net.sf.ehcache.Cache;
import net.sf.ehcache.Element;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping()
@CrossOrigin(origins = "*")
public class LoginController {

    @Resource
    UserService userService;

    @Resource
    Cache cache;

    @PostMapping("/login")
    public ResponseBean doLogin(@RequestBody LoginVo loginVo, HttpServletResponse response){
        String account = loginVo.getAccount();
        String password = loginVo.getPassword();
        User user = userService.findUserByAccount(account);
        if (user == null){
            throw new GlobalException(ResponseEnum.LOGIN_ERROR);
        }
        if (!user.getPassword().equals(password)){
            throw new GlobalException(ResponseEnum.LOGIN_ERROR);
        }
        // 颁发cookie
        String ticket = UUIDUtils.getUUID();

        Cookie cookie = new Cookie("userTicket", ticket);
        cookie.setMaxAge(60*60);
        response.addCookie(cookie);

        Element element = new Element("user" + ticket, user);
        cache.put(element);
        // 得到用户
        System.out.println(cache.get("user" + ticket).getObjectValue());
        // 保存全局对象
        UserHolder.saveUser(user);
        user.setUserTicket(ticket);

        return ResponseBean.success(user, 1);
    }

    @RequestMapping("/getUserByCookie")
    public ResponseBean getUserByCookie(HttpServletRequest request){
        User user = userService.findUserByCookie(request);
        return ResponseBean.success(user, 1);
    }

}
