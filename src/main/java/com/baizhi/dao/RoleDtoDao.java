package com.baizhi.dao;

import com.baizhi.entity.RoleDto;

import java.util.List;

public interface RoleDtoDao {
    int deleteByPrimaryKey(Integer id);

    int insert(RoleDto record);

    int insertSelective(RoleDto record);

    RoleDto selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RoleDto record);

    int updateByPrimaryKey(RoleDto record);

    List<RoleDto> selectByid(String id);
}