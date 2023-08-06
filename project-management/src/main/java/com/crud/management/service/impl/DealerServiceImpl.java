package com.crud.management.service.impl;

import com.crud.management.pojo.Dealer;
import com.crud.management.repository.DealerRepository;
import com.crud.management.service.DealerService;
import com.crud.utils.RandomNumberUtils;
import com.crud.vo.ResponseBean;
import com.crud.vo.ResponseEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Optional;

@Service
public class DealerServiceImpl implements DealerService {

    public static final Integer IS_ENABLED = 1;

    public static final double DEFAULT_FEE_RATE = 0.1;

    @Resource
    DealerRepository dealerRepository;

    @Override
    public ResponseBean findAllDealers() {
        List<Dealer> dealers = dealerRepository.findByEnable(IS_ENABLED);
        if (dealers.size() == 0){
            return ResponseBean.fail(ResponseEnum.EMPTY_ERROR);
        } else {
            return ResponseBean.success(dealers, dealers.size());
        }
    }

    @Override
    public ResponseBean addDealer(String dealerName) {
        // 名称不能为空
        if (dealerName == null){
            return ResponseBean.fail(ResponseEnum.INSERT_ERROR);
        }
        // 生成随机8位数用户编号，number不能重复
        String number = RandomNumberUtils.generateRandomNumber();
        Dealer existDealer = dealerRepository.findByDealerNumber(number);
        if (existDealer != null){
            return ResponseBean.fail(ResponseEnum.DEALER_EXIST_ERROR);
        }
        // 插入新代理
        Dealer dealer = new Dealer();
        dealer.setDealerName(dealerName);
        dealer.setDealerNumber(number);
        dealer.setEnable(IS_ENABLED);
        dealer.setFeeRate(DEFAULT_FEE_RATE);
        dealerRepository.save(dealer);
        return ResponseBean.success();
    }

    @Override
    public ResponseBean disableDealer(Long id) {
        Optional<Dealer> dealerOptional = dealerRepository.findById(id);
        if (dealerOptional.isPresent()){
            Dealer dealer = dealerOptional.get();
            if (dealer.getEnable() == 0){
                return ResponseBean.fail(ResponseEnum.DISABLE_ERROR);
            }
            dealer.setEnable(0);
            dealerRepository.save(dealer);
            return ResponseBean.success();
        } else {
            return ResponseBean.fail(ResponseEnum.EMPTY_ERROR);
        }
    }

    @Override
    public ResponseBean findAll(Pageable pageable) {
        Page<Dealer> dealerPage = dealerRepository.findDealerByEnable(pageable, IS_ENABLED);
        List<Dealer> dealers = dealerPage.getContent();
        int totalElements = (int) dealerPage.getTotalElements();
        if (dealers.size() == 0){
            return ResponseBean.fail(ResponseEnum.EMPTY_ERROR);
        } else {
            return ResponseBean.success(dealers, totalElements);
        }
    }
}
