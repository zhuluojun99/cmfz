package com.baizhi.service;

import com.baizhi.entity.Chapter;

import java.util.List;
import java.util.Map;

public interface ChapterService {
    //查询一个专辑的所有章节
    Map<String,Object> queryAll(String album_id,Integer page, Integer rows);
    //添加一条记录
    void addChapter(Chapter chapter);
    //修改一条记录
    void UpadataChapter(Chapter chapter);
    //批量删除
    void DeleteChapter(String album_id,String[] id);
    //查询一个专辑的所有章节不分页
    List<Chapter> query(String album_id);

}
