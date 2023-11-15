package com.czq.blog.service.impl;

import com.czq.blog.mapper.CommentsMapper;
import com.czq.blog.mapper.SysUserMapper;
import com.czq.blog.pojo.dto.CommentParamDto;
import com.czq.blog.pojo.entity.Comment;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.pojo.vo.CommentVo;
import com.czq.blog.pojo.vo.UserVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.CommentsService;
import com.czq.blog.utils.UserThreadLocal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.ArrayList;

@Service
@Slf4j
public class CommentsServiceImp  implements CommentsService {
    @Autowired
    CommentsMapper commentsMapper;
    @Autowired
    SysUserMapper sysUserMapper;
    /**
     * 显示文章评论
     * @param articleId
     * @return
     */
    public Result commentsByArticleId(Long articleId) {
        List<Comment> commentList=commentsMapper.getFirstComment(articleId);
        List<CommentVo> commentVoList=new ArrayList<>();
        for(Comment comment:commentList){
            CommentVo commentVo=new CommentVo();
            BeanUtils.copyProperties(comment,commentVo);
            SysUser userById = sysUserMapper.findUserById(comment.getAuthorId());
            UserVo userVo=new UserVo();
            BeanUtils.copyProperties(userById,userVo);
            commentVo.setAuthor(userVo);
            commentVo.setCreateDate(new Date(comment.getCreateDate()).toString());
            List<Comment> children=commentsMapper.getChildren(comment.getId());
            List<CommentVo> commentVos=new ArrayList<>();
            for (Comment child : children) {
                CommentVo commentVo1=new CommentVo();
                BeanUtils.copyProperties(child,commentVo1);
                SysUser userById1 = sysUserMapper.findUserById(child.getAuthorId());
                UserVo userVo1=new UserVo();
                BeanUtils.copyProperties(userById1,userVo1);
                commentVo1.setAuthor(userVo1);
                commentVo1.setCreateDate(new Date(child.getCreateDate()).toString());
                commentVo1.setToUser(userVo);
                commentVos.add(commentVo1);
            }
            commentVo.setChildrens(commentVos);


            commentVoList.add(commentVo);
        }
        return Result.success(commentVoList);
    }

    /**
     * 评论功能
     * @param commentParamDto
     */
    public void changeComment(CommentParamDto commentParamDto) {
        SysUser sysUser = UserThreadLocal.get();
        Comment comment = new Comment();
        comment.setArticleId(commentParamDto.getArticleId());
        comment.setAuthorId(sysUser.getId());
        comment.setContent(commentParamDto.getContent());
        comment.setCreateDate(System.currentTimeMillis());
        Long parent = commentParamDto.getParent();
        if (parent == null || parent == 0) {
            comment.setLevel(1);
        }else{
            comment.setLevel(2);
        }
        comment.setParentId(parent == null ? 0 : parent);
        Long toUserId = commentParamDto.getToUserId();
        comment.setToUid(toUserId == null ? 0 : toUserId);
        commentsMapper.changeComment(comment);

    }
}
