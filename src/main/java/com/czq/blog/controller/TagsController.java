package com.czq.blog.controller;

import com.czq.blog.pojo.vo.TagVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/tags")
@Slf4j
public class TagsController {
    @Autowired
    TagService tagService;
    @GetMapping("/hot")
    public Result listHotTags(){
        int limit=1;
        List<TagVo> tagVoList=tagService.getListHotTags(limit);
        return Result.success(tagVoList);
    }

    @GetMapping
    public Result getAllTags(){
        return  tagService.getAllTags();
    }

    @GetMapping("/detail")
    public Result getAllTagsDetail(){
        return  tagService.getAllTagsDetail();
    }

    @GetMapping("detail/{id}")
    public Result findDetailById(@PathVariable("id") Long id){
        return tagService.findDetailById(id);
    }
}
