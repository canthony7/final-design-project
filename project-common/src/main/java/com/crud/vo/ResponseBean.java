package com.crud.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseBean {

    private Integer code;
    private String message;
    private Object data;
    private Integer total;

    public static ResponseBean success(){
        return new ResponseBean(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), null, null);
    }

    public static ResponseBean success(Object data, Integer total){
        return new ResponseBean(ResponseEnum.SUCCESS.getCode(), ResponseEnum.SUCCESS.getMessage(), data, total);
    }

    public static ResponseBean fail(ResponseEnum responseEnum){
        return new ResponseBean(responseEnum.getCode(), responseEnum.getMessage(), null, null);
    }

}
