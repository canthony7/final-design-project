package com.crud.management.service.impl;

import com.crud.management.dto.FeeDto;
import com.crud.management.pojo.Fee;
import com.crud.management.pojo.Project;
import com.crud.management.repository.FeeRepository;
import com.crud.management.repository.ProjectRepository;
import com.crud.management.service.FeeService;
import com.crud.utils.DateUtils;
import com.crud.vo.ResponseBean;
import com.crud.vo.ResponseEnum;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class FeeServiceImpl implements FeeService {


    @Resource
    FeeRepository feeRepository;

    @Resource
    ProjectRepository projectRepository;

    @Override
    public ResponseBean findFeeByMonth(String feeType, LocalDate startDate, LocalDate endDate) {
        // 如果类型为空，返回错误
        if (feeType == null){
//            return ResponseBean.fail(ResponseEnum.FIND_ERROR);
            return findAll(startDate, endDate);
        }
        // 如果起始日期或结束日期为空，则根据类型查询
        if (startDate == null || endDate == null){
            List<Fee> feeList = feeRepository.findByFeeType(feeType);
            Map<String, Object> map = TotalFeeAndList(feeList);
            return ResponseBean.success(map, feeList.size());
        }
        // 如果两个日期都不为空，则根据日期和类型查询
        List<Fee> feeList = feeRepository.findByFeeTypeAndCreateTimeBetween(feeType, startDate, endDate);
        Map<String, Object> map = TotalFeeAndList(feeList);
        return ResponseBean.success(map, feeList.size());
    }

    @Override
    public ResponseBean findAllType() {
        List<Fee> list = feeRepository.findAll();
        List<String> typeList = new ArrayList<>();
        for (Fee fee : list) {
            typeList.add(fee.getFeeType());
        }
        // 不重复的类型
        List<String> listWithoutDuplicates = typeList.stream().distinct().collect(Collectors.toList());
        return ResponseBean.success(listWithoutDuplicates, listWithoutDuplicates.size());
    }

    // 查询所有的列表和总盈利。不再使用
    @Override
    public ResponseBean findAll(LocalDate startDate, LocalDate endDate) {
        Map<String, Object> resultMap;
        List<Fee> feeList;
        //1. 如果起始时间和结束时间为空，则直接返回所有数据，并计算总收入

        if (startDate == null || endDate == null){
            feeList = feeRepository.findAll();
        } else {
            //2. 如果时间不为空，则查询范围内数据
            feeList = feeRepository.findByCreateTimeBetween(startDate, endDate);
        }
        resultMap = TotalIncomeAndList(feeList);
        return ResponseBean.success(resultMap, feeList.size());
    }

    @Override
    public ResponseBean findFeeByProject(Long id) {
        if (id == null){
            return ResponseBean.fail(ResponseEnum.FIND_ERROR);
        }
        List<Fee> feeList = feeRepository.findByProject_Id(id);
        Map<String, Object> map = TotalIncomeAndList(feeList);
        map.remove("avgIncome");
        return ResponseBean.success(map, feeList.size());
    }

    @Override
    public ResponseBean findFeeByDealer(Long id) {
        if (id == null){
            return ResponseBean.fail(ResponseEnum.FIND_ERROR);
        }
        // 1. 根据经销商id查询项目
        List<Project> list = projectRepository.findByDealer_Id(id);
        List<Project> projectList = new ArrayList<>();
        // 2. 根据查询每个项目的代理费，一个项目只有一条代理费
        for (Project project : list) {
            Fee fee = feeRepository.findByFeeTypeAndProject_Id("代理费", project.getId());
            if (fee != null){
                project.setDealerFee(fee.getFee());
            }
            projectList.add(project);
        }
        return ResponseBean.success(projectList, projectList.size());
    }

    @Override
    public ResponseBean saveFee(String feeType, Double feeNumber, Long projectId) {
        Fee fee = new Fee();
        fee.setFeeType(feeType);
        fee.setFee(feeNumber);
        fee.setCreateTime(DateUtils.getNowLocalDate());
        if (projectId != null){
            Optional<Project> optionalProject = projectRepository.findById(projectId);
            if (optionalProject.isPresent()){
                Project project = optionalProject.get();
                fee.setProject(project);
            }
        }
        feeRepository.save(fee);
        return ResponseBean.success();
    }

    @Override
    public ResponseBean insertFee(FeeDto feeDto) {
        String feeType = feeDto.getFeeType();
        if (feeType.equals("人工费") || feeType.equals("代理费")){
            return ResponseBean.fail(ResponseEnum.INSERT_TYPE_ERROR);
        }
        Fee fee = new Fee();
        fee.setFeeType(feeDto.getFeeType());
        fee.setFee(feeDto.getFee());
        fee.setCreateTime(DateUtils.getNowLocalDate());
        feeRepository.save(fee);
        return ResponseBean.success();
    }

    // 插入额外的功能费以及代理费
    @Override
    public ResponseBean insertAdditionalFee(FeeDto feeDto) {
        Long projectId = feeDto.getProjectId();
        Optional<Project> optionalProject = projectRepository.findById(projectId);
        if (optionalProject.isEmpty()){
            return ResponseBean.fail(ResponseEnum.INSERT_ERROR);
        }
        // 1 插入功能收入
        Fee functionFee = new Fee();
        functionFee.setFeeType(feeDto.getFeeType());
        functionFee.setProject(optionalProject.get());
        functionFee.setCreateTime(DateUtils.getNowLocalDate());
        functionFee.setFee(feeDto.getFee());
        feeRepository.save(functionFee);

        // 2 插入代理费用
//        Fee dealerFee = new Fee();
//        functionFee.setFeeType("代理费");
//        functionFee.setProject(optionalProject.get());
//        functionFee.setCreateTime(DateUtils.getNowLocalDate());
//        feeRepository.save(functionFee);
        return ResponseBean.success();
    }

    public Map<String, Object> TotalFeeAndList(List<Fee> list){
        double totalFee = 0;
        for (Fee fee : list) {
            totalFee = totalFee + fee.getFee();
        }
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("feeList", list);
        resultMap.put("totalFee", totalFee);
        return resultMap;
    }

    public Map<String, Object> TotalIncomeAndList(List<Fee> feeList){
        double totalIncome = 0;
        double aveIncome = 0;
        for (Fee fee : feeList) {
            if (fee.getFeeType().contains("费")){
                totalIncome = totalIncome - fee.getFee();
            } else {
                totalIncome = totalIncome + fee.getFee();
            }
        }
        aveIncome = totalIncome / 3;
        Map<String, Object> map = new HashMap<>();
        map.put("feeList", feeList);
        map.put("totalIncome", totalIncome);
        map.put("avgIncome", aveIncome);
        return map;
    }
}
