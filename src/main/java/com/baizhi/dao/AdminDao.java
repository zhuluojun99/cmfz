package com.baizhi.dao;

import com.baizhi.entity.Admin;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminDao {
    Admin selectbyname(String username);
    List<Admin> queryAll();

    //查询出所有的普通管理员
    List<Admin> querycommon(@Param("page") Integer page, @Param("rows") Integer rows);

    //查询现有条数
    Integer selectCount();
}
