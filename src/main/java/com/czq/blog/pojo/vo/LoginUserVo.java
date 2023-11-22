package com.czq.blog.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class LoginUserVo implements Serializable {

    private Long id;

    private String account;

    private String nickname;

    private String avatar;

    private String password;
}
