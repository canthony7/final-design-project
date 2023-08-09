package com.crud.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

@Data
@ApiModel(value = "费用传输对象")
public class FeeDto {

    @ApiModelProperty(value = "费用类型")
    private String feeType;

    @ApiModelProperty(value = "费用")
    private Double fee;

    @ApiModelProperty(value = "项目id")
    private Long projectId;

}
