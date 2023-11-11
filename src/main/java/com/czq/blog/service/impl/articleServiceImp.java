package com.czq.blog.service.impl;

import com.czq.blog.mapper.ArticleMapper;
import com.czq.blog.pojo.dto.PageParamsDto;
import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.pojo.vo.ArticleVo;
import com.czq.blog.service.ArticleService;
import com.czq.blog.service.SysUserService;
import com.czq.blog.service.TagService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import net.sf.jsqlparser.expression.DateTimeLiteralExpression;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    TagService tagService;
    @Autowired
    SysUserService sysUserService;

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
