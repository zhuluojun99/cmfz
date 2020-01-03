package com.baizhi.service;

import com.baizhi.entity.Article;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

public interface ArticleService {
    //查询所有
    Map<String,Object> queryAll(Integer page,Integer rows);
    //批量删除文章
    void deleteAll(String[] id);
    //上传图片
    Map<String,Object> upload(MultipartFile img, HttpSession session, HttpServletRequest request);
    //查看图片
    Map<String,Object> getimg(HttpSession session,HttpServletRequest request);
    //添加一条记录
    void insertArtcle(Article article);
    //查询一条记录
    Article queryByid(String id);
    //修改一条记录
    void updataArticle(Article article);
    //查询所有的记录---上师言教
    List<Article> query(String id);

    //查询出所有上师的文章
    List<Article> queryByAll();
}
