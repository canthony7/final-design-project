package com.crud.utils;

import org.springframework.stereotype.Component;

import java.util.UUID;

public class UUIDUtils {

    public static String getUUID(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
