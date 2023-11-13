package com.czq.blog.pojo.vo;

import lombok.Data;

@Data
public class LoginUserVo {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;

    private String password;
}
