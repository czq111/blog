package com.czq.blog.utils;

import com.czq.blog.pojo.entity.SysUser;

public class UserThreadLocal {
    private UserThreadLocal(){};
    private static final ThreadLocal<SysUser> LOCAL=new ThreadLocal<>();
    public static void put(SysUser sysUser){
        LOCAL.set(sysUser);
    }
    public static void get(){
        LOCAL.get();
    }
    public  static void remove(){
        LOCAL.remove();
    }
}
