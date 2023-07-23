package com.crud.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * cookie工具类
 */
public class CookieUtils {

    /**
     * 1.获取cookie  返回对象，中文value还是要String cookieValue = URLDecoder.decode(c.getValue(), "utf-8");
     *
     * @param request
     * @param name    要获取的name
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request, String name) {
        //创建空的cookie对象
        Cookie cookie = null;
        Cookie[] cookies = request.getCookies();
        //遍历cookie数组
        if (cookies != null) {
            for (Cookie c : cookies) {
                String cookieKey = null;
                try {
                    //解决取中文乱码问题
                    cookieKey = URLDecoder.decode(c.getName(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                //找到要找的cookie
                if (name.equals(cookieKey)) {
                    cookie = c;
                    break;
                }
            }
        }
        return cookie;
    }

    /**
     * 2.返回要查找cookie的Value
     * @param request
     * @param key
     * @return
     */
    public static String  getCookieValue(HttpServletRequest request, String key)  {
        Cookie[] cookies = request.getCookies();
        try {
            if(cookies != null ){
                for (Cookie c : cookies) {
                    String name =  URLDecoder.decode(c.getName(),"UTF-8");
                    if(name.equals(key)){
                        return  URLDecoder.decode(c.getValue(),"UTF-8");
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return null ;
    }

    /**
     * 3.添加cookie   自定义时间
     *
     * @param response
     * @param name
     * @param value
     * @param maxAge   存活时间
     */
    public static void addCookie(HttpServletResponse response, String name, String value, int maxAge) {
        String nameKey = null;
        String nameValue = null;
        try {
            nameKey = URLEncoder.encode(name, "utf-8");// cookie保存中文报错
            nameValue = URLEncoder.encode(value, "utf-8");// cookie保存中文报错
        } catch (Exception e) {
            e.printStackTrace();
        }
        //创建cookie对象
        Cookie cookie = new Cookie(nameKey, nameValue);
        //设置存活时间
        cookie.setMaxAge(maxAge);
        //添加
        response.addCookie(cookie);
    }

    /**
     * 3.1添加cookie  默认时间
     *
     * @param response
     * @param name
     * @param value
     */
    public static void addCookie(HttpServletResponse response, String name, String value) {
        addCookie(response,name,value,-1);
    }

    /**
     * 3.2添加cookie  永久存在
     *
     * @param response
     * @param name
     * @param value
     */
    public static void addCookieForever(HttpServletResponse response, String name, String value) {
        addCookie(response,name,value,Integer.MAX_VALUE);
    }

    /**
     * 4.编辑cookie
     *
     * @param request
     * @param response
     * @param cookieName
     * @param cookieValue
     * @param maxAge
     */
    public static void changeCookie(HttpServletRequest request, HttpServletResponse response, String cookieName,

                                    String cookieValue, int maxAge) {
        boolean flag = false;
        Cookie cookie = getCookie(request, cookieName);
        if (cookie!=null){
            String newValue=null;
            try {
                newValue = URLEncoder.encode(cookieValue, "utf-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            cookie.setValue(newValue);
            cookie.setMaxAge(maxAge);
            flag=true;
        }
        //如果没有则调用添加方法添加cookie
        if (!flag) {
            addCookie(response, cookieName, cookieValue, maxAge);
        }
    }

    /**
     * 5.删除cookie
     * @param response
     * @param name
     */
    public static void delCookie( HttpServletResponse response, String name) {
        addCookie(response, name, "", 0);

    }


}


