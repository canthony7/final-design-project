package com.crud.controller;

import com.crud.pojo.Goods;
import com.crud.repository.GoodsRepository;
import com.crud.service.GoodsService;
import com.crud.vo.ResponseBean;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService goodsService;

    @Resource
    GoodsRepository goodsRepository;

    @GetMapping("/findGoodsByCategory/{id}")
    public ResponseBean findGoodsByCategory(@PathVariable Long id){
        List<Goods> goods = goodsService.findGoodsByCategoryId(id);
        return ResponseBean.success(goods, goods.size());
    }

    @GetMapping("/findGoodsByKeyword/{keyword}")
    public ResponseBean findGoodsByKeyword(@PathVariable String keyword){
        List<Goods> goods = goodsService.findGoodsByKeyword(keyword);
        return ResponseBean.success(goods, goods.size());
    }

    @GetMapping()
    public ResponseBean findAllGoods(){
        List<Goods> goods = goodsRepository.findAll();
        return ResponseBean.success(goods, goods.size());
    }

}
