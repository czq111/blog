package com.czq.blog.controller;

import com.czq.blog.pojo.vo.LoginUserVo;
import com.czq.blog.result.Result;
import com.czq.blog.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    SysUserService sysUserService;
    @GetMapping("/currentUser")
    public Result getCurrentUser(@RequestHeader("Authorization") String token){
        sysUserService.getCurrentUser(token);
        return sysUserService.getCurrentUser(token);
    }
}
