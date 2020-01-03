package com.baizhi.service;

import com.baizhi.entity.User;

import javax.servlet.http.HttpSession;
import java.util.Map;

public interface UserService {
    //登录
     Map<String,Object> selectByLogin(String phone, String password, String code, HttpSession session);
     //注册
    Map<String,Object> insert(User user);
}
