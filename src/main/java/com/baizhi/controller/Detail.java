package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.entity.Chapter;
import com.baizhi.service.AlbumService;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("detail")
public class Detail {
    @Autowired
    private AlbumService albumService;
    @Autowired
    private ChapterService chapterService;
    @RequestMapping("wen")
    public List<Map<String,Map<String,Object>>> wen(String id, String uid){
        List<Album> query = albumService.query();
        List<Chapter> query1 = chapterService.query(id);
        List<Map<String,Map<String,Object>>> list = new ArrayList<>();
        Map<String,Map<String,Object>> map1 = new HashMap<>();
        for (Album album : query) {
            Map<String,Object> map = new HashMap<>();
            map.put("thumbnail",album.getImg());
            map.put("title",album.getTitle());
            map.put("title",album.getTitle());
            map.put("score",album.getScore());
            map.put("author",album.getAuthor());
            map.put("broadcast",album.getBroadcaster());
            map.put("set_count",album.getCount());
            map.put("create_date",album.getCreate_date());
            map1.put("introduction",map);
            list.add(map1);
        }
        for (Chapter chapter : query1) {
            Map<String,Object> map = new HashMap<>();
            map.put("title",chapter.getTitle());
            map.put("download_url",chapter.getSrc());
            map.put("size",chapter.getSrc());
            map.put("duration",chapter.getDuration());
            map1.put("list",map);
            list.add(map1);
        }
        return list;
    }
}
