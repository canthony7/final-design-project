package com.crud.management.service.impl;

import com.crud.management.dto.ProjectDto;
import com.crud.management.dto.StatusDto;
import com.crud.management.pojo.Dealer;
import com.crud.management.pojo.Fee;
import com.crud.management.pojo.Project;
import com.crud.management.repository.DealerRepository;
import com.crud.management.repository.FeeRepository;
import com.crud.management.repository.ProjectRepository;
import com.crud.management.service.FeeService;
import com.crud.management.service.ProjectService;
import com.crud.utils.DateUtils;
import com.crud.vo.ResponseBean;
import com.crud.vo.ResponseEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectServiceImpl implements ProjectService {

    @Resource
    ProjectRepository projectRepository;

    @Resource
    DealerRepository dealerRepository;

    @Resource
    FeeService feeService;

    @Resource
    FeeRepository feeRepository;

    @Override
    public ResponseBean findAllProjects() {
        List<Project> projects = projectRepository.findAll();
        return ResponseBean.success(projects, projects.size());
    }

    @Override
    public ResponseBean addNewProject(ProjectDto projectDto) {
        if (projectDto == null){
            return ResponseBean.fail(ResponseEnum.INSERT_ERROR);
        }
        Project project = new Project();
        project.setProjectName(projectDto.getProjectName());
        project.setBackend(projectDto.getBackend());
        project.setFrontend(projectDto.getFrontend());
        project.setCollege(projectDto.getCollege());
        project.setFrontDeveloper(projectDto.getFrontDeveloper());
        project.setBackDeveloper(projectDto.getBackDeveloper());
        project.setOrderTime(DateUtils.getNowLocalDate());
        project.setStatus("进行中");

        // 1. 判断是否拥有代理id
        Dealer dealer = null;
        Long dealerId = projectDto.getDealerId();
        if (dealerId != null){
            // 2. 如果有代理id: 插入项目信息 -> 根据代理费率进行计算并插入到fee表 -> 更新代理费率
            Optional<Dealer> dealerOptional = dealerRepository.findById(dealerId);
            if (dealerOptional.isPresent()){
                dealer = dealerOptional.get();
                Double feeRate = dealer.getFeeRate();
                project.setDealer(dealer);
                // 插入项目信息
                Project insertedProject = projectRepository.save(project);
                // 将项目收入插入到fee table
                feeService.saveFee("收入", projectDto.getProjectFee(), insertedProject.getId());
                // 计算代理费用插入到fee table
                Double dealerFee = feeRate * projectDto.getProjectFee();
                feeService.saveFee("代理费", dealerFee, insertedProject.getId());
                // 更新代理的费率
                // dealer.setFeeRate(feeRate + 0.01D);
                dealerRepository.save(dealer);
            }
        } else {
            // 3. 如果没有代理id：插入项目信息
            Project insertedProject = projectRepository.save(project);
            feeService.saveFee("收入", projectDto.getProjectFee(), insertedProject.getId());
        }
        return ResponseBean.success();
    }

    @Override
    public ResponseBean updateProjectStatus(StatusDto statusDto) {
        Long projectId = statusDto.getId();
        String status = statusDto.getStatus();
        Optional<Dealer> optionalDealer = dealerRepository.findById(statusDto.getDealerId());
        Dealer dealer = optionalDealer.get();
        Double feeRate = dealer.getFeeRate();
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        // 如果有这个项目
        if (optionalProject.isPresent()){
            Project project = optionalProject.get();
            project.setStatus(status);
            if (status.equals("已完成")){
                // 更新代理的费率
                dealer.setFeeRate(feeRate + 0.01D);
                dealerRepository.save(dealer);
            }
            projectRepository.save(project);
            return ResponseBean.success();
        } else {
            return ResponseBean.fail(ResponseEnum.EMPTY_ERROR);
        }
    }

    @Override
    public ResponseBean deleteProject(Long id) {
        if (id == null){
            return ResponseBean.fail(ResponseEnum.DELETE_ERROR);
        }
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty()){
            return ResponseBean.fail(ResponseEnum.DELETE_ERROR);
        } else {
            Project project = optionalProject.get();
            List<Fee> fees = feeRepository.findByProject_Id(project.getId());
            feeRepository.deleteAllInBatch(fees);
            projectRepository.deleteById(project.getId());
            return ResponseBean.success();
        }
    }
}