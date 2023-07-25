package com.crud.management.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Project传输对象")
public class ProjectDto {

    @ApiModelProperty(value = "项目名称", required = true)
    private String projectName;

    @ApiModelProperty(value = "前端技术", required = true)
    private String frontend;

    @ApiModelProperty(value = "后端技术", required = true)
    private String backend;

    @ApiModelProperty(value = "大学")
    private String college;

    @ApiModelProperty(value = "前端开发者", required = true)
    private String frontDeveloper;

    @ApiModelProperty(value = "后端开发者", required = true)
    private String backDeveloper;

    @ApiModelProperty(value = "代理id, 为空表示没有代理")
    private Long dealerId;

    @ApiModelProperty(value = "项目费用", required = true)
    private Double projectFee;

}
