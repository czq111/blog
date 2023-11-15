package com.czq.blog.controller;

import com.czq.blog.pojo.dto.CommentParamDto;
import com.czq.blog.result.Result;
import com.czq.blog.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comments")
public class CommentsController {
    @Autowired
    private CommentsService commentsService;

    @GetMapping("/article/{id}")
    public Result comments(@PathVariable("id") Long articleId){

        return commentsService.commentsByArticleId(articleId);

    }

    @PostMapping("/create/change")
    public Result changeComment(@RequestBody CommentParamDto commentParamDto){
        commentsService.changeComment(commentParamDto);
        return Result.success(null);
    }

}
