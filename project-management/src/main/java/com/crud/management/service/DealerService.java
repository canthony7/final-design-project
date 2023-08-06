package com.crud.management.service;

import com.crud.management.pojo.Dealer;
import com.crud.vo.ResponseBean;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DealerService {

    ResponseBean findAllDealers();

    ResponseBean addDealer(String dealerName);

    ResponseBean disableDealer(Long id);

    ResponseBean findAll(Pageable pageable);
}
