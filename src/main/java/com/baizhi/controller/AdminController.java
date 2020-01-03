package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    @ResponseBody
    public String login(String username, String password, String yzm, HttpSession session){
        Admin querybyname = adminService.querybyname(username);
        String code = (String) session.getAttribute("code");
        String tips = null;
        if(code.equalsIgnoreCase(yzm)){
            if(querybyname!=null){
                if(querybyname.getPassword().equals(password)){
                    session.setAttribute("admin",querybyname);
                    return "ok";
                }else {
                    return "用户名或密码输入错误";
                }
            }else{
                return "用户名或密码输入错误";
            }
        }else {
            return "验证码输入错误,不区分大小写!";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "redirect:/login.jsp";
    }
    @RequestMapping("adminOut")
    public void adminOut(HttpServletResponse response){
        adminService.adminOut(response);
    }
}
