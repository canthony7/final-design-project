package com.crud.management.service.impl;

import com.crud.management.dto.ProjectDto;
import com.crud.management.pojo.Project;
import com.crud.management.repository.ProjectRepository;
import com.crud.management.service.ProjectService;
import com.crud.vo.ResponseBean;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectRepository projectRepository;

    @Override
    public ResponseBean findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return ResponseBean.success(projects, projects.size());
    }

    @Override
    public ResponseBean addNewProject(ProjectDto projectDto) {
        // 1. 判断是否拥有代理id

        // 2.

        return null;
    }
}
