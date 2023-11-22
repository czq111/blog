package com.czq.blog.pojo.vo;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
@Data
public class PublishVo implements Serializable {
    @JsonSerialize(using = ToStringSerializer.class)
    Long id;
}
