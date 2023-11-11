package com.czq.blog.service;

import com.czq.blog.pojo.vo.TagVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TagService {
    List<TagVo> findTagsByAarticleId(Long id);

    List<TagVo> getListHotTags(int limit);
}
