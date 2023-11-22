package com.czq.blog.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class TagVo implements Serializable {
    private Long id;
    private String tagName;
}
