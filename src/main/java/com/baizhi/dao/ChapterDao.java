package com.baizhi.dao;

import com.baizhi.entity.Chapter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ChapterDao {
    //查询所有章节---根据专辑id
    List<Chapter> selectAll(@Param("id") String album_id, @Param("page") Integer page, @Param("rows") Integer rows);
    //查询该章节的条数
    Integer selectCount(String album_id);
    //添加一条记录
    void InsertOne(Chapter chapter);
    //修改记录
    void updataChapter(Chapter chapter);
    //删除多条记录
    void deleteChapter(String[] id);
    //删除一张专辑后列表里的歌曲全部清空
    void deleteAll(String[]  id);
    //查询一个专辑的所有章节不分页
    List<Chapter> select(String album_id);
}
