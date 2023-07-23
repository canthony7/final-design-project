package com.crud.management.controller;

import com.crud.management.service.ProjectService;
import com.crud.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/project")
@Api(value = "项目接口", tags = "项目资源相关操作")
public class ProjectController {

    @Resource
    ProjectService projectService;

    @GetMapping
    @ApiOperation(value = "查询所有项目")
    public ResponseBean findAll(){
        return projectService.findAllProjects();
    }

}
