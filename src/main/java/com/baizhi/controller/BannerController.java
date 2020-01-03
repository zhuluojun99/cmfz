package com.baizhi.controller;

import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/Banner")
public class BannerController {
    @Autowired
    private BannerService bannerService;
    @RequestMapping("QueryAll")
    @ResponseBody
    public Map<String,Object> queryAll(Integer page, Integer rows){
        return bannerService.queryAll(page, rows);
    }
    @ResponseBody
    @RequestMapping("Edit")
    public Map<String,Object> edit(String oper, Banner banner,String[] id,HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if("add".equals(oper)){
            String bannerid = UUID.randomUUID().toString().replace("-","");
            banner.setId(bannerid);
            banner.setImg(banner.getImg()+new Date().getTime());
            banner.setCreate_date(new Date());
            bannerService.addBanner(banner);
            map.put("bannerId",bannerid);
            return map;
        } else if("edit".equals(oper)){
            if("".equals(banner.getImg())){
                banner.setImg(null);
                bannerService.UpdataBanner(banner);
            }else {
                banner.setImg(null);
                bannerService.UpdataBanner(banner);
                map.put("bannerId",banner.getId());
                return map;
            }
        }else if("del".equals(oper)){
            bannerService.DeletBanner(id);
            }
        return map;
    }
    @RequestMapping("upload")
    public void bannerUpload(MultipartFile img, String bannerId, HttpSession session){
        Banner banner = new Banner();
        banner.setId(bannerId);
        banner.setImg(img.getOriginalFilename());
        bannerService.UpdataBanner(banner);
        //获取绝对路径
        String path = session.getServletContext().getRealPath("/images");
        //判断文件夹是否存在
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        try {
            img.transferTo(new File(path,img.getOriginalFilename()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @RequestMapping("/QueryBanner")
    public String QueryAllByStatus(Model mo){
        List<Banner> banners = bannerService.queryAllbyStatus();
            mo.addAttribute("queryAll1",banners.get(0));
            mo.addAttribute("queryAll2",banners.get(1));
            mo.addAttribute("queryAll3",banners.get(2));
        return "backstage";
    }
    @RequestMapping("bannerOut")
    public void bannerOut(HttpServletResponse response){
        bannerService.bannerOut(response);
    }
    @RequestMapping("getvalue")
    @ResponseBody
    public List<Object> getvalue(){
        return bannerService.queryBymonth();
    }

    @RequestMapping("getday")
    @ResponseBody
    public List<Integer> getday(){
        return bannerService.queryByday();
    }
}
