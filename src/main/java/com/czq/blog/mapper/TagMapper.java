package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TagMapper {
    List<Tag> findTagsByArticleId(Long id);

    @Select("select tag_id from ms_article_tag group by tag_id order by count(*) desc limit #{limit}")
    List<Long> getHotTagsId(int limit);

    List<Tag> getTagsByTagsId(List<Long> tagsId);
}
