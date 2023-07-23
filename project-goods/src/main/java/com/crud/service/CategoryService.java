package com.crud.service;

import com.crud.pojo.Category;

import java.util.List;

public interface CategoryService {

    // 查询所有商品类别
    List<Category> findAllCategory();

}
