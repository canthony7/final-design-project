package com.crud.management.controller;

import com.crud.management.dto.ProjectDto;
import com.crud.management.service.ProjectService;
import com.crud.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
    @ApiOperation(value = "插入一个新项目", notes = "两种插入方式：1. 有代理则通过代理id插入；2. 没代理则通过自行插入")
    public ResponseBean addNewProject(@RequestBody ProjectDto projectDto){
        return projectService.addNewProject(projectDto);
    }
}
