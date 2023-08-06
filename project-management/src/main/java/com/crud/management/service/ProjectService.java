package com.crud.management.service;

import com.crud.management.dto.ProjectDto;
import com.crud.management.dto.StatusDto;
import com.crud.vo.ResponseBean;

public interface ProjectService {

    ResponseBean findAllProjects();

    ResponseBean addNewProject(ProjectDto projectDto);

    ResponseBean updateProjectStatus(StatusDto statusDto);

    ResponseBean deleteProject(Long id);

}
