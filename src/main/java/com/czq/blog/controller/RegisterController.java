package com.czq.blog.controller;

import com.czq.blog.pojo.vo.LoginUserVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/register")
public class RegisterController {
    @Autowired
    LoginService loginService;
    @PostMapping
    public Result register(@RequestBody LoginUserVo loginUserVo){
        return loginService.register(loginUserVo);
    }
}
