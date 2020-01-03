package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Article;
import com.baizhi.entity.Banner;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ArticleService;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RestController
public class First_page {
    @Autowired
    private BannerService bannerService;
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ArticleService articleService;

    @RequestMapping("first_page")
    public Map<String, List<Map<String, Object>>> first_page(String uid, String type, String sub_type) {
        Map<String, List<Map<String, Object>>> map = new HashMap<>();
        if(uid!=null){
            if ("all".equals(type)) {
                List<Banner> banners = bannerService.queryAllbyStatus();
                List<Album> query = albumService.query();
                List<Article> query1 = articleService.query(uid);
                for (Banner banner : banners) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("thumbnail", banner.getImg());
                    map1.put("desc", banner.getTitle());
                    map1.put("id", banner.getId());
                    list.add(map1);
                    map.put("header", list);
                }
                for (Album album : query) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("thumbnail", album.getImg());
                    map1.put("title", album.getTitle());
                    map1.put("author", album.getAuthor());
                    if ("wen".equals(type)) {
                        map1.put("type", 0);
                    }
                    if ("si".equals(type)) {
                        map1.put("type", 1);
                    }
                    map1.put("set_count", album.getCount());
                    map1.put("create_date", album.getCreate_date());
                    list.add(map1);
                    map.put("album", list);
                }
                for (Article article : query1) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    Map<String, Object> map1 = new HashMap<>();
                    map1.put("id", article.getId());
                    map1.put("title", article.getTitle());
                    map1.put("author", article.getAuthor());
                    map1.put("content", article.getContent());
                    list.add(map1);
                    map.put("artical", list);
                }
                if ("ssyj".equals(sub_type)) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    List<Article> query2 = articleService.query(uid);
                    Map<String, Object> map1 = new HashMap<>();
                    for (Article article : query2) {
                        map1.put("id", article.getId());
                        map1.put("title", article.getTitle());
                        map1.put("author", article.getAuthor());
                        map1.put("content", article.getContent());
                        list.add(map1);
                        map.put("artical", list);
                    }
                } else if ("xmfy".equals(sub_type)) {
                    List<Map<String, Object>> list = new ArrayList<>();
                    List<Article> query3 = articleService.queryByAll();
                    Map<String, Object> map1 = new HashMap<>();
                    for (Article article : query3) {
                        map1.put("id", article.getId());
                        map1.put("title", article.getTitle());
                        map1.put("author", article.getAuthor());
                        map1.put("content", article.getContent());
                        list.add(map1);
                        map.put("artical", list);
                    }
                }
            }
            return map;
        }else {
            return null;
        }

    }
}

