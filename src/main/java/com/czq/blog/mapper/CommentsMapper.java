package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.Article;
import com.czq.blog.pojo.entity.Comment;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CommentsMapper {
    @Select("select * from ms_comment where article_id=#{articleId} and level=1")
    List<Comment> getFirstComment(Long articleId);

    @Select("select * from ms_comment where parent_id=#{id} and level<>1")
    List<Comment> getChildren(Long id);

    @Insert(" insert into ms_comment(content, create_date, article_id, author_id, parent_id, to_uid, level)\n" +
            "        VALUES\n" +
            "            (#{content},#{createDate},#{articleId},#{authorId},#{parentId},#{toUid},#{level})")
    void changeComment(Comment comment);
}
