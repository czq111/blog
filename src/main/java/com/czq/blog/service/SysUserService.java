package com.czq.blog.service;

import com.czq.blog.pojo.entity.SysUser;
import org.springframework.stereotype.Service;

@Service
public interface SysUserService {
    SysUser findUserById(Long authorId);
}
