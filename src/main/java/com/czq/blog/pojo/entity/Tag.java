package com.czq.blog.pojo.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Tag implements Serializable {
    private Long id;

    private String avatar;

    private String tagName;
}
