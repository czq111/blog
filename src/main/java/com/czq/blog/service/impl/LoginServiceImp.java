package com.czq.blog.service.impl;

import com.alibaba.fastjson.JSON;
import com.czq.blog.common.ErrorCode;
import com.czq.blog.mapper.LoginMapper;
import com.czq.blog.pojo.dto.LoginDto;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.pojo.vo.LoginUserVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.LoginService;
import com.czq.blog.service.SysUserService;
import com.czq.blog.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

@Service
public class LoginServiceImp implements LoginService {
    private static final String slat = "mszlu!@###";
    @Autowired
    LoginMapper loginMapper;
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public Result login(LoginDto loginDto) {
        String account = loginDto.getAccount();
        String password = loginDto.getPassword();
        if(StringUtils.isEmpty(account)||StringUtils.isEmpty(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        String pwd=DigestUtils.md5DigestAsHex((password+slat).getBytes());
        SysUser user=loginMapper.login(account,pwd);
        if(user==null){
            return Result.fail(ErrorCode.ACCOUNT_PWD_NOT_EXIST.getCode(),ErrorCode.ACCOUNT_PWD_NOT_EXIST.getMsg());
        }
        String token = JWTUtils.createToken(user.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token, JSON.toJSONString(user),1, TimeUnit.DAYS);
        return Result.success(token);
    }

    @Override
    public Result logout(String token) {
        redisTemplate.delete("TOKEN_"+token);
        return Result.success(null);
    }

    /**
     * 用户注册
     * @param loginUserVo
     * @return
     */
    @Transactional
    public Result register(LoginUserVo loginUserVo) {
        String account = loginUserVo.getAccount();
        String nickname = loginUserVo.getNickname();
        String password = loginUserVo.getPassword();
        if(StringUtils.isEmpty(account)||StringUtils.isEmpty(nickname)||StringUtils.isEmpty(password)){
            return Result.fail(ErrorCode.PARAMS_ERROR.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        SysUser sysUser= loginMapper.findUserByAccount(account);
        if(sysUser!=null){
            return Result.fail(ErrorCode.ACCOUNT_EXIST.getCode(),ErrorCode.PARAMS_ERROR.getMsg());
        }
        sysUser = new SysUser();
        sysUser.setNickname(nickname);
        sysUser.setAccount(account);
        sysUser.setPassword(DigestUtils.md5DigestAsHex((password+slat).getBytes()));
        sysUser.setCreateDate(System.currentTimeMillis());
        sysUser.setLastLogin(System.currentTimeMillis());
        sysUser.setAvatar("/static/img/logo.b3a48c0.png");
        sysUser.setAdmin(1); //1 为true
        sysUser.setDeleted(0); // 0 为false
        sysUser.setSalt("");
        sysUser.setStatus("");
        sysUser.setEmail("");
        loginMapper.insertUser(sysUser);
        String token = JWTUtils.createToken(sysUser.getId());
        redisTemplate.opsForValue().set("TOKEN_"+token,JSON.toJSONString(sysUser),1,TimeUnit.DAYS);
        return Result.success(token);
    }
}
