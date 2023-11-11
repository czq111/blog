package com.czq.blog.controller;

import com.czq.blog.pojo.dto.PageParamsDto;
import com.czq.blog.result.Result;
import com.czq.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/articles")
@Slf4j
public class ArticleController {
    @Autowired
    ArticleService articleService;
    /**
     * 首页文章列表
     * @param pageParamsDto
     * @return
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParamsDto pageParamsDto){
        return Result.success(articleService.listArticle(pageParamsDto));
    }




}
