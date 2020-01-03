package com.baizhi.service;

import com.baizhi.entity.Album;

import java.util.List;
import java.util.Map;

public interface AlbumService {
    //查询所有专辑
    Map<String,Object> queryAll(Integer page, Integer rows);
    //添加一版专辑
    void insertAlbum(Album album);
    //修改专辑信息
    void updataAlbum(Album album);
    //删除专辑和此专辑下的所有歌曲
    void deleteAlbum(String[] id);
    //查所有专辑不分页
    List<Album> query();
}
