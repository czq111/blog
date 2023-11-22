package com.czq.blog.pojo.vo;
import lombok.Data;

import java.io.Serializable;

@Data
public class CategoryVo implements Serializable {

    private Long id;

    private String avatar;

    private String categoryName;
}
