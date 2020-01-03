package com.baizhi.controller;

import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import it.sauronsoftware.jave.Encoder;
import it.sauronsoftware.jave.MultimediaInfo;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/Chapter")
public class ChapterController {
    @Autowired
    private ChapterService chapterService;
    @ResponseBody
    @RequestMapping("QueryAll")
    public Map<String,Object> queryAll(String album_id,Integer page, Integer rows){
        return chapterService.queryAll(album_id,page,rows);
    }
    @ResponseBody
    @RequestMapping("Edit")
    public Map<String,Object> edit(String oper, Chapter chapter, String[] id, HttpSession session){
        Map<String,Object> map = new HashMap<>();
        if("add".equals(oper)){
            String Chapterid = UUID.randomUUID().toString().replace("-","");
            chapter.setId(Chapterid);
            chapterService.addChapter(chapter);
            map.put("charpterId",Chapterid);
            return map;
        } else if("edit".equals(oper)){
            if("".equals(chapter.getSrc())){
                chapter.setSrc(null);
                chapterService.UpadataChapter(chapter);
            }else {
                chapter.setSrc(null);
                chapterService.UpadataChapter(chapter);
                map.put("charpterId",chapter.getId());
                return map;
            }
        }else if("del".equals(oper)){
            chapterService.DeleteChapter(chapter.getAlbum_id(),id);
        }
        return map;
    }

    @RequestMapping("upload")
    public void bannerUpload(MultipartFile src, String chapterId, HttpSession session){
        //获取上传绝对路径
        String path = session.getServletContext().getRealPath("/music");
        Chapter chapter = new Chapter();
        chapter.setId(chapterId);
        chapter.setSrc(new Date().getTime()+"_"+src.getOriginalFilename());
        //判断文件夹是否存在
        File f = new File(path);
        if(!f.exists()){
            f.mkdirs();
        }
        try {
            src.transferTo(new File(path,chapter.getSrc()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //获取到文件大小
        Long len = src.getSize();
        String printSize = getPrintSize(len);
        chapter.setSize(printSize);
        //获取到文件的时长
        Encoder encoder = new Encoder();
        long ls = 0;
        MultimediaInfo m;
        File file = new File(path,chapter.getSrc());
        try {
            m = encoder.getInfo(file);
            ls = m.getDuration();
            String format = ls/60000+"分"+(ls/1000-ls/60000*60)+"秒";
            chapter.setDuration(format);
        } catch (Exception e) {
            System.out.println("获取音频时长有误：" + e.getMessage());
        }
        chapterService.UpadataChapter(chapter);
    }
    //文件下载
    @RequestMapping("download")
    public void dowload(String filename, HttpServletRequest request, HttpServletResponse response) throws Exception {
        //接收前台的下载请求
        //为文件名设置编码---获得一个编码格式正确的文件名
        //如果下载的文件存在中文的话 就进行编码操作
        String newfileName = URLEncoder.encode(filename,"UTF-8");
        String[] s = newfileName.split("_");
        newfileName = s[1];
        //先获得服务的路径
        String path = request.getSession().getServletContext().getRealPath("/music");
        FileInputStream fileInputStream = null;
        //设置文件下载的方式---把新的文件名传入
        response.setHeader("content-disposition", "attachment;filename=" + newfileName);
        //设置文件响应的类型
        //获取到文件的后缀名  不包含.
        String hz = FilenameUtils.getExtension(filename);
        //通过后缀名获取到文件的MIME类型
        String mimeType = request.getSession().getServletContext().getMimeType("." + hz);
        //设置响应的类型
        response.setContentType(mimeType);
        fileInputStream = new FileInputStream(new File(path, filename));
        //给前台打印出数据---获得一个打印流
        ServletOutputStream outputStream = null;
        outputStream = response.getOutputStream();
        //文件传输
        byte[] b = new byte[2048];
        while (true) {
            int i = 0;
            i = fileInputStream.read(b, 0, b.length);
            if (i == -1) break;
            //向服务端响应数据
            outputStream.write(b, 0, i);
        }
        //传输完毕进行资源的关闭
        if(outputStream!=null){
            outputStream.close();
        }
        if(fileInputStream!=null){
            fileInputStream.close();
        }
    }
    public  String getPrintSize(long size) {
        //如果字节数少于1024，则直接以B为单位，否则先除于1024，后3位因太少无意义
        if (size < 1024) {
            return String.valueOf(size) + "B";
        } else {
            size = size / 1024;
        }
        //如果原字节数除于1024之后，少于1024，则可以直接以KB作为单位
        //因为还没有到达要使用另一个单位的时候
        //接下去以此类推
        if (size < 1024) {
            return String.valueOf(size) + "KB";
        } else {
            size = size / 1024;
        }
        if (size < 1024) {
            //因为如果以MB为单位的话，要保留最后1位小数，
            //因此，把此数乘以100之后再取余
            size = size * 100;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "MB";
        } else {
            //否则如果要以GB为单位的，先除于1024再作同样的处理
            size = size * 100 / 1024;
            return String.valueOf((size / 100)) + "."
                    + String.valueOf((size % 100)) + "GB";
        }
    }
}
