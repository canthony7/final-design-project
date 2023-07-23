package com.crud.management.service;
import com.crud.vo.ResponseBean;

import java.time.LocalDate;
import java.util.List;

public interface FeeService {

    ResponseBean findFeeByMonth(String feeType, LocalDate startDate, LocalDate endDate);

    ResponseBean findAllType();

    ResponseBean findAll(LocalDate startDate, LocalDate endDate);

    ResponseBean findFeeByProject(Long id);

    ResponseBean findFeeByDealer(Long id);
}
