package com.czq.blog.pojo.dto;

import com.czq.blog.pojo.vo.CategoryVo;
import com.czq.blog.pojo.vo.TagVo;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParamDto body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}