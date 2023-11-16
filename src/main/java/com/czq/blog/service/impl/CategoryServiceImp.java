package com.czq.blog.service.impl;

import com.czq.blog.mapper.CategoryMapper;
import com.czq.blog.pojo.entity.Category;
import com.czq.blog.result.Result;
import com.czq.blog.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@Slf4j
public class CategoryServiceImp implements CategoryService {
    @Autowired
    CategoryMapper categoryMapper;
    @Override
    public Result getCategorys() {
        List<Category> category=categoryMapper.getCategorys();
        return Result.success(category);
    }
}
