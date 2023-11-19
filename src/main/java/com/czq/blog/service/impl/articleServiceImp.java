package com.czq.blog.service.impl;

import com.czq.blog.mapper.ArticleMapper;
import com.czq.blog.mapper.TagMapper;
import com.czq.blog.pojo.dto.ArticleParam;
import com.czq.blog.pojo.dto.PageParamsDto;
import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.entity.ArticleBody;
import com.czq.blog.pojo.entity.Category;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.pojo.vo.*;
import com.czq.blog.result.Result;
import com.czq.blog.service.ArticleService;
import com.czq.blog.service.SysUserService;
import com.czq.blog.service.TagService;
import com.czq.blog.service.ThreadService;
import com.czq.blog.utils.UserThreadLocal;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import net.sf.jsqlparser.expression.LongValue;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class articleServiceImp implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    TagMapper tagMapper;
    @Autowired
    TagService tagService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    ThreadService threadService;

    /**
     * 首页文章列表
     * @param pageParamsDto
     * @return
     */
    @Override
    public List<ArticleVo> listArticle(PageParamsDto pageParamsDto) {
        PageHelper.startPage(pageParamsDto.getPage(),pageParamsDto.getPageSize());
        Page<Article> articlePage=articleMapper.listArticle();
        List<Article> result = articlePage.getResult();
        List<ArticleVo> res=copyList(result);
        return res;
    }

    @Override
    public List<HotArticleVo> getHotArticle(int limit) {
        List<HotArticleVo> articles=articleMapper.getHotArticle(limit);
        return articles;
    }

    @Override
    public List<HotArticleVo> getNewArticle() {
        List<HotArticleVo> newArticleVos=articleMapper.getNewArticle();
        return newArticleVos;
    }

    @Override
    public List<ListArchivesVo> getListArchives() {
        List<ListArchivesVo> listArchivesVos=articleMapper.getListArchives();
        return listArchivesVos;
    }

    /**
     * 通过id获取文章详情
     * @param id
     * @return
     */
    public Result view(Long id) {
        Article article=articleMapper.getArticleById(id);
        ArticleVo articleVo=new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        Long bodyId = article.getBodyId();
        ArticleBodyVo articleBodyVo=articleMapper.getBodyById(bodyId);
        articleVo.setBody(articleBodyVo);
        Long categoryId = article.getCategoryId();
        Category category=articleMapper.getCategoryById(categoryId);
        CategoryVo categoryVo=new CategoryVo();
        BeanUtils.copyProperties(category,categoryVo);
        //List<CategoryVo> categoryVoList=new ArrayList<>();
        //categoryVoList.add(categoryVo);
        articleVo.setCategory(categoryVo);
        if(article.getCategoryId()!=null){
            articleVo.setTags(tagService.findTagsByAarticleId(article.getId()));
        }
        if(article.getAuthorId()!=null){
            articleVo.setAuthor(sysUserService.findUserById(article.getAuthorId()).getNickname());
        }
        articleVo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(article.getCreateDate())));

        //通过id查看文章详情，说明文章浏览量加一，此时利用线程池来更新浏览量
        threadService.updateViewCount(articleMapper,article);

        return Result.success(articleVo);
    }

    /**
     * 发布文章
     * @param articleParam
     * @return
     */
    @Transactional
    public Result publish(ArticleParam articleParam) {
        //ms_article
        //ms_article_body
        //ms_article_tag
        Article article=new Article();
        BeanUtils.copyProperties(articleParam,article);
        article.setCreateDate(System.currentTimeMillis());
        SysUser sysUser = UserThreadLocal.get();
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(articleParam.getCategory().getId());
        articleMapper.insert(article);
        //文章id
        Long id = article.getId();
        ArticleBody articleBody=new ArticleBody();
        BeanUtils.copyProperties(articleParam.getBody(),articleBody);
        articleBody.setArticleId(id);
        articleMapper.insertBody(articleBody);
        Long articleBodyId = articleBody.getId();
        article.setBodyId(articleBodyId);
        articleMapper.update(article);
        List<TagVo> tags = articleParam.getTags();
        List<Long> ids=new ArrayList<>();
        for (TagVo tag : tags) {
            ids.add(tag.getId());
        }
        tagMapper.insertArticleTag(id,ids);
        PublishVo publishVo=new PublishVo();
        publishVo.setId(id);

        return Result.success(publishVo);
    }

    private List<ArticleVo> copyList(List<Article> result) {
        List<ArticleVo> list=new ArrayList<>();
        for (Article article : result) {
            ArticleVo articleVo=new ArticleVo();
            BeanUtils.copyProperties(article,articleVo);
            articleVo.setCreateDate(new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date(article.getCreateDate())));
            if(article.getCategoryId()!=null){
                articleVo.setTags(tagService.findTagsByAarticleId(article.getId()));
            }
            if(article.getAuthorId()!=null){
                articleVo.setAuthor(sysUserService.findUserById(article.getAuthorId()).getNickname());
            }
            list.add(articleVo);
        }
        return list;
    }
}
