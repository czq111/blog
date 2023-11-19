package com.czq.blog.service;

import com.czq.blog.pojo.dto.ArticleParam;
import com.czq.blog.pojo.dto.PageParamsDto;
import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.vo.ArticleVo;
import com.czq.blog.pojo.vo.HotArticleVo;
import com.czq.blog.pojo.vo.ListArchivesVo;
import com.czq.blog.result.Result;
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

    List<HotArticleVo> getHotArticle(int limit);

    List<HotArticleVo> getNewArticle();

    List<ListArchivesVo> getListArchives();

    /**
     * 通过id获取文章详情
     * @param id
     * @return
     */
    Result view(Long id);

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    Result publish(ArticleParam articleParam);
}
