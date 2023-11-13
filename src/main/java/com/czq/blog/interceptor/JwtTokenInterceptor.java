package com.czq.blog.interceptor;

import com.alibaba.fastjson.JSON;
import com.czq.blog.common.ErrorCode;
import com.czq.blog.pojo.entity.SysUser;
import com.czq.blog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenInterceptor implements HandlerInterceptor {
    @Autowired
    RedisTemplate redisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //静态方法直接放行
        if(! (handler instanceof HandlerMethod)){
            log.info("静态方法直接放行");
            return true;
        }

        String token = request.getHeader("Authorization");
        if(token==null){
            Result res = Result.fail(-999, "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(res));
            log.info("未登录");
            return false;
        }
        String o = (String)redisTemplate.opsForValue().get("TOKEN_" + token);
        SysUser sysUser = JSON.parseObject(o, SysUser.class);
        if(sysUser==null){
            Result res = Result.fail(-999, "未登录");
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSON.toJSONString(res));
            log.info("未登录");
            return false;
        }
        log.info("已经登录，放行");
        return true;
    }
}
