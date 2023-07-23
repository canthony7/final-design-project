package com.crud.controller;

import com.crud.pojo.Category;
import com.crud.repository.CategoryRepository;
import com.crud.vo.ResponseBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
public class CategoryController {

    @Resource
    CategoryRepository categoryRepository;

    @GetMapping("/category")
    public ResponseBean findAllCategory(){
        List<Category> categories = categoryRepository.findAll();
        return ResponseBean.success(categories, categories.size());
    }

}
