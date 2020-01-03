package com.baizhi.dao;

import com.baizhi.entity.Album;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AlbumDao {
    //查询所有专辑
    List<Album> selectAll(@Param("page") Integer page, @Param("rows") Integer rows);
    //查询专辑条数
    Integer selectCount();
    //添加一条专辑
    void insertAlbum(Album album);
    //修改专辑
    void updataAlbum(Album album);
    //批量删除专辑
    void deleteAlbum(String[] id);
    //改变数量--添加
    void updataAddCount(@Param("id") String id);
    //改变数量--删除
    void updataDeleteCount(@Param("id") String id,@Param("count") int count);
    //查所有专辑
    List<Album> select();
}
