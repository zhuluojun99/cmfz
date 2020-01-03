package com.baizhi.dao;


import com.baizhi.entity.Banner;
import com.baizhi.entity.MontnDto;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BannerDao {
    //查询所有图片
    List<Banner> selectAll(@Param("page") Integer page,@Param("rows") Integer rows);
    //查询现有条数
    Integer selectCount();
    //添加一条记录
    void InsertOne(Banner banner);
    //根据id进行修改
    void updataBanner(Banner banner);
    //删除图片
    void deleteBanner(String[] id);
    //查询所有激活的图片
    List<Banner> selectAllbyStatus();
    //查询全部
    List<Banner> queryAll();
    //查询出本年每月添加图片的个数
    MontnDto selectByMonth();
    //查询出最近七天的轮播图数量
    List<Integer> selectByday();

}
