package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.entity.ArticleBody;
import com.czq.blog.pojo.entity.Category;
import com.czq.blog.pojo.vo.ArticleBodyVo;
import com.czq.blog.pojo.vo.HotArticleVo;
import com.czq.blog.pojo.vo.ListArchivesVo;
import com.github.pagehelper.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

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

    @Select("select * from ms_article where id=#{id}")
    Article getArticleById(Long id);

    @Select("select * from ms_article_body where id=#{bodyId}")
    ArticleBodyVo getBodyById(Long bodyId);

    @Select("select * from ms_category where id=#{categoryId}")
    Category getCategoryById(Long categoryId);

    @Update("update ms_article set view_counts=#{newCount} where id=#{id} and view_counts=#{viewCounts}")
    void updateViewCount(Long id, int viewCounts, int newCount);

    @Update("update ms_article set comment_counts=#{commentCounts} where id=#{articleId} ")
    void updateCommentCounts(int commentCounts, Long articleId);

    void insert(Article article);

    void insertBody(ArticleBody articleBody);

    void update(Article article);

    Page<Article> listArticle2(Long categoryId);

    Page<Article> listArticle3(List<Long> articleIds);
}
