package com.czq.blog.service;

import com.czq.blog.pojo.dto.CommentParamDto;
import com.czq.blog.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface CommentsService {
    /**
     * 显示文章评论
     * @param articleId
     * @return
     */
    Result commentsByArticleId(Long articleId);

    /**
     * 评论功能
     * @param commentParamDto
     */
    void changeComment(CommentParamDto commentParamDto);
}
