package com.czq.blog.service.impl;

import com.czq.blog.mapper.SysUserMapper;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SysUserServiceImp implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Override
    public SysUser findUserById(Long authorId) {

        return sysUserMapper.findUserById(authorId);
    }
}
