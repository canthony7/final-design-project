package com.crud.management.service;
import com.crud.management.pojo.Fee;
import com.crud.vo.ResponseBean;

import java.time.LocalDate;
import java.util.List;

public interface FeeService {

    ResponseBean findFeeByMonth(String feeType, LocalDate startDate, LocalDate endDate);

    ResponseBean findAllType();

    ResponseBean findAll(LocalDate startDate, LocalDate endDate);

    ResponseBean findFeeByProject(Long id);

    ResponseBean findFeeByDealer(Long id);

    // 插入一条费用，project可以为空
    ResponseBean saveFee(String feeType, Double feeNumber, Long projectId);

}
