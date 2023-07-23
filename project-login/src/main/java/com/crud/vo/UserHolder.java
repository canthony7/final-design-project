package com.crud.vo;

import com.crud.pojo.User;

public class UserHolder {

    public static ThreadLocal<User> tl = new ThreadLocal<User>();

    public static void saveUser(User user){
        tl.set(user);
    }

    public static User getUser(){
        return tl.get();
    }

    public static void removeUser(){
        tl.remove();
    }

}
