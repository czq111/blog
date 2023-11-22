package com.czq.blog.service;

import com.czq.blog.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface CategoryService {
    Result getCategorys();

    Result getCategorysDetail();

    Result categoriesDetailById(Long id);
}
