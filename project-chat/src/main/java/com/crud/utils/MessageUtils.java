package com.crud.utils;

import com.alibaba.fastjson2.JSON;
import com.crud.pojo.ServerMessage;

public class MessageUtils {

    public static String getMessage(boolean isSystemMessage, String fromName, Object message){
        // 如果是系统消息，就没有fromName
        ServerMessage serverMessage = new ServerMessage();
        serverMessage.setSystemMessage(isSystemMessage);
        serverMessage.setMessage(message);
        if (fromName != null){
            serverMessage.setFromName(fromName);
        }
        return JSON.toJSONString(serverMessage);
    }

}
