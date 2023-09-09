package com.crud.management.controller;

import com.crud.management.dto.FeeDto;
import com.crud.management.service.FeeService;
import com.crud.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDate;

@RestController
@RequestMapping("/fee")
@CrossOrigin(origins = "*")
@Api(value = "账单接口", tags = "账单资源相关操作")
public class FeeController {

    @Resource
    FeeService feeService;

    @GetMapping("findByMonthAndType")
    @ApiOperation(value = "根据类型和时间查询账单列表", notes = "起始日期和结束日期非必须要传, 如果不传则根据类型查询账单")
    public ResponseBean findByMonthAndType(@RequestParam(required = false) String feeType,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                       @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return feeService.findFeeByMonth(feeType, startDate, endDate);
    }

    @GetMapping("/type")
    @ApiOperation(value = "查询所有费用类型")
    public ResponseBean findAllType(){
        return feeService.findAllType();
    }

    @GetMapping()
    @ApiOperation(value = "查询所有订单并统计净盈利（该接口已不再使用）")
    public ResponseBean findAll(@RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate startDate,
                                @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate endDate){
        return feeService.findAll(startDate, endDate);
    }

    @GetMapping("/dealer/{id}")
    @ApiOperation(value = "根据经销商的id查询他关联的项目，以及每个项目的代理费")
    public ResponseBean findFeeByDealer(@PathVariable Long id){
        return feeService.findFeeByDealer(id);
    }

    @GetMapping("/project/{id}")
    @ApiOperation(value = "查询某个项目的所有开销，并返回这个项目净赚多少钱")
    public ResponseBean findFeeByProject(@PathVariable Long id){
        return feeService.findFeeByProject(id);
    }

    @PostMapping
    @ApiOperation(value = "插入一条费用信息，如果projectId为空则是普通费用；如果不为空则是客户后期增加功能")
    public ResponseBean insertFee(@RequestBody FeeDto feeDto){
        if (feeDto.getProjectId() == null){
            return feeService.insertFee(feeDto);
        } else {
            return feeService.insertAdditionalFee(feeDto);
        }

    }

}
