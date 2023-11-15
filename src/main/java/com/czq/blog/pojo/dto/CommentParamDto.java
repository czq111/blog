package com.czq.blog.pojo.dto;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

@Data
public class CommentParamDto {
    @JsonSerialize(using = ToStringSerializer.class)
    private Long articleId;

    private String content;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long parent;

    @JsonSerialize(using = ToStringSerializer.class)
    private Long toUserId;
}
