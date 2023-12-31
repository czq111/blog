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

    @Select("select * from ms_tag ")
    List<Tag> getAllTags();

    void insertArticleTag(Long id, List<Long> ids);

    @Select("select * from ms_tag where id=#{id}")
    Tag getAllTagById(Long id);

    @Select("select article_id from ms_article_tag where tag_id=#{tagId}")
    List<Long> getArticleIds(Long tagId);
}
