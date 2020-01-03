package com.baizhi.controller;

import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/account")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("login")
    public Map<String,Object> login(String phone, String password, String code, HttpSession session){
        return userService.selectByLogin(phone, password, code, session);
    }
    @RequestMapping("regist")
    public Map<String,Object> regist(User user){
        return userService.insert(user);
    }
}
