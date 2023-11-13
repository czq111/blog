package com.czq.blog.mapper;

import com.czq.blog.pojo.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface LoginMapper {
    @Select("select * from ms_sys_user where account=#{account} and password=#{pwd}")
    SysUser login(String account, String pwd);
}
