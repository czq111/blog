package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.*;

@Mapper
public interface CategoryMapper {
    @Select("select * from ms_category")
    List<Category> getCategorys();
}
