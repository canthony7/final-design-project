package com.crud.management.dto;

import com.crud.management.pojo.Dealer;
import com.crud.management.pojo.Project;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

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

}
