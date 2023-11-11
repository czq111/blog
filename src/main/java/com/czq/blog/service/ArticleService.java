package com.czq.blog.service;

import com.czq.blog.pojo.dto.PageParamsDto;
import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.vo.ArticleVo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ArticleService {
    /**
     * 首页文章列表
     * @param pageParamsDto
     * @return
     */
    List<ArticleVo> listArticle(PageParamsDto pageParamsDto);
}