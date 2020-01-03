package com.baizhi.controller;

import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/Article")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @RequestMapping("QueryAll")
    @ResponseBody
    public Map<String ,Object> queryAll(Integer page, Integer rows){
        return articleService.queryAll(page,rows);
    }
    @ResponseBody
    @RequestMapping("Edit")
    public void edit(String[] id,String oper){
        if("del".equals(oper)){
            articleService.deleteAll(id);
        }
    }
    //富文本图片的上传
    @RequestMapping("uploadImg")
    @ResponseBody
    public Map<String,Object> upload(MultipartFile img, HttpServletRequest request, HttpSession session){
        return articleService.upload(img, session, request);
    }
    //富文本图片的查看
    @RequestMapping("getImg")
    @ResponseBody
    public Map<String,Object> getImg(HttpServletRequest request, HttpSession session){
        return articleService.getimg(session, request);
    }
    //添加一条记录
    @RequestMapping("insert")
    @ResponseBody
    public void insetArticle(Article article){
        article.setId(UUID.randomUUID().toString().replace("-",""));
        article.setCreate_date(new Date());
        articleService.insertArtcle(article);
    }

    //查询一条记录的详细
    @RequestMapping("queryByid")
    @ResponseBody
    public Article queryByid(String id){
        return articleService.queryByid(id);
    }

    //修改一条记录
    @RequestMapping("updata")
    @ResponseBody
    public void updataArticle(Article article){
        articleService.updataArticle(article);
    }
}
