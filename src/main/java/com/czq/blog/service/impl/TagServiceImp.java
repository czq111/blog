package com.czq.blog.service.impl;

import com.czq.blog.mapper.TagMapper;
import com.czq.blog.pojo.entity.Tag;
import com.czq.blog.pojo.vo.TagVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.TagService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@Slf4j
public class TagServiceImp implements TagService {
    @Autowired
    TagMapper tagMapper;
    @Override
    public List<TagVo> findTagsByAarticleId(Long id) {
        List<Tag> tags=tagMapper.findTagsByArticleId(id);
        List<TagVo> res=new ArrayList<>();
        for (Tag tag : tags) {
            TagVo tagVo=new TagVo();
            BeanUtils.copyProperties(tag,tagVo);
            res.add(tagVo);
        }
      //  log.info("那日容"+res);
        return res;
    }

    @Override
    public List<TagVo> getListHotTags(int limit) {
        List<Long> tagsId=tagMapper.getHotTagsId(limit);
        if(CollectionUtils.isEmpty(tagsId)){
            return Collections.emptyList();
        }
        List<Tag> tags=tagMapper.getTagsByTagsId(tagsId);
       // log.info(tags.toString());
        List<TagVo> tagVos=new ArrayList<>();
        for (Tag tag : tags) {
            TagVo tagVo=new TagVo();
            BeanUtils.copyProperties(tag,tagVo);
            tagVos.add(tagVo);
        }
        return tagVos;
    }

    @Override
    public Result getAllTags() {
        List<Tag> tags=tagMapper.getAllTags();
        List<TagVo> tagVos=new ArrayList<>();
        for (Tag tag : tags) {
            TagVo tagVo=new TagVo();
            BeanUtils.copyProperties(tag,tagVo);
            tagVos.add(tagVo);
        }
        return Result.success(tagVos);
    }

    @Cacheable(cacheNames = "tagCache")
    public Result getAllTagsDetail() {
        List<Tag> tags=tagMapper.getAllTags();
        return Result.success(tags);
    }

    @Cacheable(cacheNames = "tagCacheDetail",key = "#id")
    public Result findDetailById(Long id) {
        Tag tag=tagMapper.getAllTagById(id);
        return Result.success(tag);

    }
}
