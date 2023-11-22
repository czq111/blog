package com.czq.blog.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class UserVo implements Serializable {

    private String nickname;

    private String avatar;

    private Long id;
}