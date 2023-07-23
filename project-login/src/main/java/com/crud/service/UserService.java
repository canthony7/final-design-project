package com.crud.service;

import com.crud.pojo.User;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface UserService {

    User findUserByAccount(String account);

    User findUserByCookie(HttpServletRequest request);

}
