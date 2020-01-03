package com.baizhi.service.impl;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.afterturn.easypoi.excel.entity.ExportParams;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Banner;
import com.baizhi.entity.MontnDto;
import com.baizhi.service.BannerService;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class BannerServiceImpl implements BannerService {
    @Autowired
    private BannerDao bannerDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> queryAll(Integer page,Integer rows) {
        Map<String,Object> map = new HashMap<String,Object>();
        //总条数
        Integer count = bannerDao.selectCount();
        map.put("records",count);
        //总页数
        Integer pageCount = count%rows==0 ? count/rows:count/rows+1;
        map.put("total",pageCount);
        //起始条数
        page = (page-1)*rows;
        //查到的数据
        List<Banner> list = bannerDao.selectAll(page, rows);
        map.put("rows",list);
        return map;
    }

    @Override
    public void addBanner(Banner banner) {
        bannerDao.InsertOne(banner);
    }

    @Override
    public void UpdataBanner(Banner banner) {
        bannerDao.updataBanner(banner);
    }

    @Override
    public void DeletBanner(String[] id) {
        bannerDao.deleteBanner(id);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Banner> queryAllbyStatus() {
        List<Banner> banners = bannerDao.selectAllbyStatus();
        List<Banner> banner = new ArrayList<>();
        for (int i = 0; i <=2; i++) {
            banner.add(banners.get(i));
        }
        System.out.println(banner.size());
        return banner;
    }

    @Override
    public void bannerOut(HttpServletResponse response) {
        List<Banner> list = bannerDao.queryAll();
        for (Banner banner : list) {
            banner.setImg("E:\\ideacode\\cmfz\\src\\main\\webapp\\images\\"+banner.getImg());
        }
        response.setHeader("content-disposition","attachment;filename=banner.xls");
        ServletOutputStream outputStream=null;
        Workbook workbook = ExcelExportUtil.exportBigExcel(new ExportParams("轮播图", "图片信息"), Banner.class, list);
        try {
            outputStream = response.getOutputStream();
            workbook.write(outputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputStream.close();
                workbook.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Object> queryBymonth() {
        MontnDto montnDto = bannerDao.selectByMonth();
        List<Object> list = new ArrayList<>();
        if(montnDto!=null){
            list.add(montnDto.getJan());
            list.add(montnDto.getFeb());
            list.add(montnDto.getMar());
            list.add(montnDto.getApr());
            list.add(montnDto.getMay());
            list.add(montnDto.getJun());
            list.add(montnDto.getJul());
            list.add(montnDto.getAug());
            list.add(montnDto.getSept());
            list.add(montnDto.getOct());
            list.add(montnDto.getNov());
            list.add(montnDto.getDecr());
        }else {
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
            list.add(0);
        }
        return list;
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Integer> queryByday() {
        return bannerDao.selectByday();
    }
}
