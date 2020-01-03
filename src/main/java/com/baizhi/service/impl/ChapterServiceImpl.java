package com.baizhi.service.impl;

import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ChapterServiceImpl implements ChapterService {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private AlbumDao albumDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String, Object> queryAll(String album_id,Integer page, Integer rows) {
        Map<String,Object> map = new HashMap<String,Object>();
        //总条数
        Integer count = chapterDao.selectCount(album_id);
        map.put("records",count);
        //总页数
        Integer pageCount = count%rows==0 ? count/rows:count/rows+1;
        map.put("total",pageCount);
        //起始条数
        page = (page-1)*rows;
        //查到的数据
        List<Chapter> list = chapterDao.selectAll(album_id, page, rows);
        map.put("rows",list);
        return map;
    }

    @Override
    public void addChapter(Chapter chapter) {
        chapterDao.InsertOne(chapter);
        albumDao.updataAddCount(chapter.getAlbum_id());
    }

    @Override
    public void UpadataChapter(Chapter chapter) {
        chapterDao.updataChapter(chapter);
    }

    @Override
    public void DeleteChapter(String album_id,String[] id) {
        chapterDao.deleteChapter(id);
        albumDao.updataDeleteCount(album_id,id.length);
    }

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Chapter> query(String album_id) {
        return chapterDao.select(album_id);
    }
}
