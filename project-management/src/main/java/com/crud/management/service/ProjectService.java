package com.crud.management.service;

import com.crud.management.dto.ProjectDto;
import com.crud.management.pojo.Project;
import com.crud.vo.ResponseBean;

import java.util.List;

public interface ProjectService {

    ResponseBean findAllProjects();

    ResponseBean addNewProject(ProjectDto projectDto);

}
