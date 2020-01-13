package com.baizhi.controller;

import com.baizhi.service.AdminService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/Admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @RequestMapping("login")
    @ResponseBody
    public String login(String username, String password, String yzm, HttpSession session){
        String code = (String) session.getAttribute("code");
        String tips = null;
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken usernamePasswordToken = new UsernamePasswordToken(username, password);
        if(code.equalsIgnoreCase(yzm)){
            try {
                subject.login(usernamePasswordToken);
                    return "ok";
            } catch (UnknownAccountException e) {
                return "用户名错误";
            } catch (IncorrectCredentialsException e) {
                return "密码出错";
            }
        }else {
            return "验证码输入错误,不区分大小写!";
        }
    }
    @RequestMapping("logout")
    public String logout(HttpSession session){
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login.jsp";
    }
    @RequestMapping("adminOut")
    public void adminOut(HttpServletResponse response){
        adminService.adminOut(response);
    }

    @RequestMapping("QueryAll")
    @ResponseBody
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        return adminService.querycommon(page, rows);
    }
}
