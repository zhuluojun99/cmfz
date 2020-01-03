package com.baizhi.service;

import com.baizhi.entity.Banner;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

public interface BannerService {
    //查询所有轮播图
    Map<String,Object> queryAll(Integer page,Integer rows);
    //添加一条记录
    void addBanner(Banner banner);
    void UpdataBanner(Banner banner);
    //删除
    void DeletBanner(String[] id);
    //查询所有激活的图片
    List<Banner> queryAllbyStatus();
    //导出轮播图信息
    void bannerOut(HttpServletResponse response);
    //查询出每月的图片数量
    List<Object> queryBymonth();
    //查询出最近七天上传轮播图数量
    List<Integer> queryByday();
}
