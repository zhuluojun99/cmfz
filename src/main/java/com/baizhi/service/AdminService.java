package com.baizhi.service;

import com.baizhi.entity.Admin;

import javax.servlet.http.HttpServletResponse;

public interface AdminService {
    Admin querybyname(String username);
    //导出用户信息
    void adminOut(HttpServletResponse response);
}
