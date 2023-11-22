package com.czq.blog.pojo.dto;

import lombok.Data;

@Data
public class PageParamsDto {
    private int page=1;
    private int pageSize=10;
    Long categoryId;
    Long tagId;
}
