package com.czq.blog.service;

import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.pojo.vo.LoginUserVo;
import com.czq.blog.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface SysUserService {
    SysUser findUserById(Long authorId);

    /**
     * 获取当前登录用户
     * @param token
     * @return
     */
    Result getCurrentUser(String token);
}
