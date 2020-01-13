package com.baizhi.dao;

import com.baizhi.entity.PermDto;

import java.util.List;

public interface PermDtoDao {
    int deleteByPrimaryKey(String id);

    int insert(PermDto record);

    int insertSelective(PermDto record);

    PermDto selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(PermDto record);

    int updateByPrimaryKey(PermDto record);

    //根据用户角色查询所有权限
    List<PermDto> selectByid(String id);
}