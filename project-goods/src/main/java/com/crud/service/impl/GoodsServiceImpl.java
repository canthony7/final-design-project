package com.crud.service.impl;

import com.crud.pojo.Goods;
import com.crud.repository.GoodsRepository;
import com.crud.service.GoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Resource
    GoodsRepository goodsRepository;

    @Override
    public List<Goods> findGoodsByCategoryId(Long categoryId) {
        return goodsRepository.findGoodsByCategoryId(categoryId);
    }

    @Override
    public List<Goods> findGoodsByKeyword(String keyword) {
        List<Goods> goods = goodsRepository.findByGoodNameOrContentLike(keyword);
        return goods;
    }
}
