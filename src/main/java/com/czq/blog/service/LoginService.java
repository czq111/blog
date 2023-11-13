package com.czq.blog.service;

import com.czq.blog.pojo.dto.LoginDto;
import com.czq.blog.result.Result;
import org.springframework.stereotype.Service;

@Service
public interface LoginService {
    Result login(LoginDto loginDto);
}
