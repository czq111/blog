package com.czq.blog.controller;

import com.czq.blog.result.Result;
import com.czq.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequestMapping("/categorys")
public class CategoryController {
    @Autowired
    CategoryService categoryService;
    @GetMapping
    public Result getCategorys(){
        return categoryService.getCategorys();
    }

    @GetMapping("/detail")
    public Result getCategorysDeatil(){
        return categoryService.getCategorysDetail();
    }

    @GetMapping("detail/{id}")
    public Result categoriesDetailById(@PathVariable("id") Long id){
        return categoryService.categoriesDetailById(id);
    }
}
