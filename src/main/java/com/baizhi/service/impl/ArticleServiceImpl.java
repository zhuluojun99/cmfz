package com.baizhi.service.impl;

import com.baizhi.dao.ArticleDao;
import com.baizhi.entity.Article;
import com.baizhi.service.ArticleService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleDao articleDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<String,Object>();
        //总条数
        Integer count = articleDao.selectCount();
        map.put("records",count);
        //总页数
        Integer pageCount = count%rows==0 ? count/rows:count/rows+1;
        map.put("total",pageCount);
        //起始条数
        page = (page-1)*rows;
        //查到的数据
        List<Article> list = articleDao.selectAll(page, rows);
        map.put("rows",list);
        return map;
    }

    @Override
    public void deleteAll(String[] id) {
        articleDao.deleteArticle(id);
    }

    @Override
    public Map<String, Object> upload(MultipartFile img, HttpSession session, HttpServletRequest request) {
        //新建一个map集合
        Map<String,Object> map = new HashMap<>();
        //获得服务器文件上传的路径
        String path = session.getServletContext().getRealPath("/kindeditor/img");
        //判断文件夹是否存在
        File file = new File(path);
        if(!file.exists()){
            file.mkdirs();
        }
        //获取到文件的真实名字--加上时间戳防止重名
        String originalFilename = img.getOriginalFilename();
        String name = new Date().getTime()+"_"+originalFilename;
        //进行文件的上传
        try {
            img.transferTo(new File(path,name));
            //进行map集合的数据封装
            map.put("error",0);
            //获取到网址的前置标签
            String scheme = request.getScheme();
            //获取到服务器的id地址--需要进行截取
            InetAddress localHost = InetAddress.getLocalHost();
            String host = localHost.toString().split("/")[1];
            //获取到端口号
            int serverPort = request.getServerPort();
            //获取到项目名 http://localhost:3050/cmfz/Banner/QueryBanner#
            String contextPath = request.getContextPath();
            String url = scheme+"://"+host+":"+serverPort+contextPath+"/kindeditor/img/"+name;
            map.put("url",url);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public Map<String, Object> getimg(HttpSession session, HttpServletRequest request) {
        Map<String,Object> map =  new HashMap<>();
        //拿到目录下的所有图片
        String path = session.getServletContext().getRealPath("/kindeditor/img");
        File file = new File(path);
        String[] imgs = file.list();
        //新建一个list集合用来存放图片
        List<Object> list = new ArrayList<>();
        //遍历集合将图片存入集合
        for (String img : imgs) {
            Map<String,Object> imgmap =  new HashMap<>();
            imgmap.put("is_dir",false);
            imgmap.put("has_file",false);
            //获取到每个文件的大小
            File fil = new File(path,img);
            long length = fil.length();
            imgmap.put("filesize",length);
            imgmap.put("dir_path","");
            imgmap.put("is_photo",true);
            //获取到返回名字的后缀格式
            String extension = FilenameUtils.getExtension(img);
            imgmap.put("filetype",extension);
            imgmap.put("filename",img);
            //获取到文件的上传时间
            String s = img.split("_")[0];
            //进行时间格式的转换
            Long lo = Long.valueOf(s);
            Date date = new Date(lo);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            String format = sdf.format(date);
            imgmap.put("datetime",format);
            list.add(imgmap);
        }
        map.put("file_list",list);
        map.put("moveup_dir_path","");
        map.put("current_dir_path","");
        //获取到文件所在的服务器位置
        try {
            String scheme = request.getScheme();    //前置
            InetAddress localHost = InetAddress.getLocalHost();
            String host = localHost.toString().split("/")[1];
            int serverPort = request.getServerPort();
            String contextPath = request.getContextPath();
            String url = scheme+"://"+host+":"+serverPort+contextPath+"/kindeditor/img/";
            map.put("current_url",url);
            map.put("total_count",imgs.length);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void insertArtcle(Article article) {
        articleDao.insert(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Article queryByid(String id) {
        return articleDao.selectByid(id);
    }

    @Override
    public void updataArticle(Article article) {
        if("".equals(article.getContent())){
            article.setContent(null);
        }
        articleDao.updateByPrimaryKeySelective(article);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> query(String id) {
        return articleDao.select(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Article> queryByAll() {
        return articleDao.selectByAll();
    }
}
