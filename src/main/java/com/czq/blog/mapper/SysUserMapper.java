package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface SysUserMapper {
    @Select("select * from ms_sys_user where id=#{id}")
    SysUser findUserById(Long id);
}
