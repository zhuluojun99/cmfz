package com.baizhi.controller;

import com.baizhi.entity.Album;
import com.baizhi.service.AlbumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/Album")
public class AlbumController {
    @Autowired
    private AlbumService albumService;
    @ResponseBody
    @RequestMapping("QueryAll")
    public Map<String,Object> queryAll(Integer page, Integer rows){
        return albumService.queryAll(page,rows);
    }
    @ResponseBody
    @RequestMapping("Edit")
    public Map<String,Object> edit(String oper, Album album, String[] id, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if("add".equals(oper)){
            String AlbumId = UUID.randomUUID().toString().replace("-","");
            album.setId(AlbumId);
            albumService.insertAlbum(album);
            map.put("albumId",AlbumId);
            return map;
        }else if("edit".equals(oper)){
            if("".equals(album.getImg())){
                album.setImg(null);
                albumService.updataAlbum(album);
            }else {
                album.setImg(null);
                albumService.updataAlbum(album);
                map.put("albumId",album.getId());
                return map;
            }
        }else if("del".equals(oper)){
            albumService.deleteAlbum(id);
        }
        return map;
    }
    @RequestMapping("upload")
    public void bannerUpload(MultipartFile img, String albumId, HttpSession session){
        Album album = new Album();
        album.setId(albumId);
        album.setImg(new Date().getTime()+"_"+img.getOriginalFilename());
        albumService.updataAlbum(album);
        //获取绝对路径
        String path = session.getServletContext().getRealPath("/img");
        //判断文件夹是否存在
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        //上传文件
        try {
            img.transferTo(new File(path,album.getImg()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
