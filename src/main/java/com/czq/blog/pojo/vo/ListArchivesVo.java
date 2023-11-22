package com.czq.blog.pojo.vo;

import lombok.Data;

import java.io.Serializable;

@Data
public class ListArchivesVo implements Serializable {
    String year;
    String month;
    int count;
}
