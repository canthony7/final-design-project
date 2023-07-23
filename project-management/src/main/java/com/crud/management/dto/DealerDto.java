package com.crud.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "接收代理商数据实体类")
public class DealerDto {

    @ApiModelProperty(value = "代理名称")
    private String dealerName;

}
