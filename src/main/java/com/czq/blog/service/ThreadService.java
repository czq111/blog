package com.czq.blog.service;

import com.czq.blog.mapper.ArticleMapper;
import com.czq.blog.pojo.entity.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {

    @Async("taskExecutor")
    public void updateViewCount(ArticleMapper articleMapper, Article article){
        Long id = article.getId();
        int viewCounts = article.getViewCounts();
        int newCount=viewCounts+1;
        articleMapper.updateViewCount(id,viewCounts,newCount);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
