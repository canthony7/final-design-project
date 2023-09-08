package com.crud.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "更新状态dto类")
public class StatusDto {

    @ApiModelProperty(value = "项目id", required = true)
    private Long id;

    @ApiModelProperty(value = "状态", required = true)
    private String status;

    @ApiModelProperty(value = "代理id", required = false)
    private Long dealerId;

}
