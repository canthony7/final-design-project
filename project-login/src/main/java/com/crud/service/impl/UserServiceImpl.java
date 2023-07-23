package com.crud.service.impl;

import com.crud.pojo.User;
import com.crud.repository.UserRepository;
import com.crud.service.UserService;
import com.crud.utils.CookieUtils;
import net.sf.ehcache.Cache;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserRepository userRepository;

    @Resource
    Cache cache;

    @Override
    public User findUserByAccount(String account) {
        return userRepository.findByAccount(account);
    }

    @Override
    public User findUserByCookie(HttpServletRequest request) {
        Cookie cookie = CookieUtils.getCookie(request, "userTicket");
        String ticket = cookie.getValue();
        System.out.println(cache.get("user" + ticket).getTimeToLive());
        User user = ((User) cache.get("user" + ticket).getObjectValue());
        return user;
    }
}
