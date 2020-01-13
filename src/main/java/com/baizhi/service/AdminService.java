package com.baizhi.service;

import com.baizhi.entity.Admin;
import com.baizhi.entity.PermDto;
import com.baizhi.entity.RoleDto;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface AdminService {
    Admin querybyname(String username);
    //导出用户信息
    void adminOut(HttpServletResponse response);

    //查询出用户所有角色
    List<RoleDto> queryByid(String id);

    //查询出角色对应的权限
    List<PermDto> queryByuserid(String id);

    //查询出所有普通的管理员
    Map<String, Object> querycommon(Integer page, Integer rows);
}
