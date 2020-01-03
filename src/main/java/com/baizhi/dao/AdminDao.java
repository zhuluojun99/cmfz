package com.baizhi.dao;

import com.baizhi.entity.Admin;

import java.util.List;

public interface AdminDao {
    Admin selectbyname(String username);
    List<Admin> queryAll();
}
