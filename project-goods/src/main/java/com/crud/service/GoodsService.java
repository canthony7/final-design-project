package com.crud.service;

import com.crud.pojo.Goods;

import java.util.List;

public interface GoodsService {

    // 根据类别id查询商品
    List<Goods> findGoodsByCategoryId(Long categoryId);

    // 根据关键词查询商品
    List<Goods> findGoodsByKeyword(String keyword);

}
