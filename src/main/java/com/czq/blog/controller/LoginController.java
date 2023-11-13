package com.czq.blog.controller;

import com.czq.blog.pojo.dto.LoginDto;
import com.czq.blog.result.Result;
import com.czq.blog.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@Slf4j
public class LoginController {
    @Autowired
    LoginService loginService;

    /**
     * 用户登录
     * @param loginDto
     * @return
     */
    @PostMapping
    public Result login(@RequestBody LoginDto loginDto){
        Result res=loginService.login(loginDto);
        return res;
    }
}
