package com.crud.management.controller;

import com.crud.management.dto.DealerDto;
import com.crud.management.service.DealerService;
import com.crud.vo.ResponseBean;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/dealer")
@CrossOrigin(origins = "*")
@Api(value = "代理接口", tags = "代理相关资源操作")
public class DealerController {

    @Resource
    DealerService dealerService;

    @GetMapping("/{currentPage}/{pageSize}")
    @ApiOperation(value = "查询所有代理")
    public ResponseBean findAllDealer(@PathVariable Integer currentPage, @PathVariable Integer pageSize){
        Pageable pageable = PageRequest.of(currentPage, pageSize);
        return dealerService.findAll(pageable);
    }

    @PostMapping
    @ApiOperation(value = "添加一个代理")
    public ResponseBean addDealer(@RequestBody DealerDto dealer){
        return dealerService.addDealer(dealer.getDealerName());
    }

    @PutMapping("{id}")
    @ApiOperation(value = "停用一个代理")
    public ResponseBean disableDealer(@PathVariable Long id){
        return dealerService.disableDealer(id);
    }

}
