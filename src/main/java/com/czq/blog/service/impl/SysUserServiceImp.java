package com.czq.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.czq.blog.common.ErrorCode;
import com.czq.blog.mapper.SysUserMapper;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.pojo.vo.LoginUserVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.SysUserService;
import com.czq.blog.utils.JWTUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Map;

@Service
@Slf4j
public class SysUserServiceImp implements SysUserService {
    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public SysUser findUserById(Long authorId) {

        return sysUserMapper.findUserById(authorId);
    }

    /**
     * 获取当前登录用户
     * @param token
     * @return
     */
    public Result getCurrentUser(String token) {
        if (StringUtils.isEmpty(token)){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        Map<String, Object> stringObjectMap = JWTUtils.checkToken(token);
        if(StringUtils.isEmpty(stringObjectMap)||stringObjectMap==null||stringObjectMap.size()==0){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        String user = (String)redisTemplate.opsForValue().get("TOKEN_" + token);
        if(StringUtils.isEmpty(user)){
            return Result.fail(ErrorCode.NO_LOGIN.getCode(),ErrorCode.NO_LOGIN.getMsg());
        }
        SysUser sysUser= JSON.parseObject(user,SysUser.class);
        LoginUserVo loginUserVo=new LoginUserVo();
        BeanUtils.copyProperties(sysUser,loginUserVo);

        return Result.success(loginUserVo);
    }
}
