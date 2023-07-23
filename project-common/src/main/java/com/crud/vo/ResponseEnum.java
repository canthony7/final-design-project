package com.crud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum ResponseEnum {

    FAIL(400, "失败"),
    SUCCESS(200, "成功"),
    LOGIN_ERROR(401, "登录错误"),
    EMPTY_ERROR(410, "未查询到记录"),
    INSERT_ERROR(411, "插入异常"),
    DEALER_EXIST_ERROR(412, "代理号已存在，请重新插入"),

    DISABLE_ERROR(413, "该账号已禁用"),

    FIND_ERROR(414, "查询异常，参数不能为空");

    private final Integer code;
    private final String message;

}
