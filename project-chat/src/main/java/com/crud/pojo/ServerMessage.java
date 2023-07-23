package com.crud.pojo;

import lombok.Data;

// 服务端给客户端发送消息
@Data
public class ServerMessage {

    // 是否是系统消息
    private boolean isSystemMessage;
    private String fromName;
    private Object message;

}
