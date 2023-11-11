package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.Article;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select * from ms_article order by create_date desc")
    Page<Article> listArticle();
}
