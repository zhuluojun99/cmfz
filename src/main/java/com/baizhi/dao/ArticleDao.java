package com.baizhi.dao;

import com.baizhi.entity.Article;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleDao {
    int deleteByPrimaryKey(String id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKey(Article record);

    //查询所有
    List<Article> selectAll(@Param("page") Integer page,@Param("rows") Integer rows);
    //总条数
    Integer selectCount();
    //批量删除文章
    void deleteArticle(String[] id);
    //单独查看某条数据的详细信息
    Article selectByid(String id);
    //查询出改上师发布的所有文章
    List<Article> select(String id);
    //查询出所有吧的文章--所有吧上师
    List<Article> selectByAll();
}