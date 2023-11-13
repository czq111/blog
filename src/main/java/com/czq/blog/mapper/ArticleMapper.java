package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.vo.HotArticleVo;
import com.czq.blog.pojo.vo.ListArchivesVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ArticleMapper {
    @Select("select * from ms_article order by create_date desc")
    Page<Article> listArticle();

    @Select("select id,title from ms_article order by view_counts desc limit #{limit}")
    List<HotArticleVo> getHotArticle(int limit);

    @Select("select id,title from ms_article order by create_date desc limit 1")
    List<HotArticleVo> getNewArticle();

    List<ListArchivesVo> getListArchives();
}
