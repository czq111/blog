package com.czq.blog.controller;

import com.czq.blog.pojo.dto.PageParamsDto;
import com.czq.blog.pojo.vo.HotArticleVo;
import com.czq.blog.pojo.vo.ListArchivesVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.ArticleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PostMapping("/hot")
    public Result hotArticle(){
        int limit=1;
        List<HotArticleVo> hotArticleVos=articleService.getHotArticle(limit);
        return Result.success(hotArticleVos);
    }

    @PostMapping("/new")
    public Result newArticle(){
        List<HotArticleVo> newArticle=articleService.getNewArticle();
        return Result.success(newArticle);
    }

    @PostMapping("/listArchives")
    public Result listArchives(){
        List<ListArchivesVo> listArchivesVos=articleService.getListArchives();
        return Result.success(listArchivesVos);
    }

    @PostMapping("/view/{id}")
    public Result view(@PathVariable("id") Long id){
        return articleService.view(id);
    }

}
