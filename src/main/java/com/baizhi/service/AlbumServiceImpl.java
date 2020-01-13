package com.baizhi.service;

import com.baizhi.annotation.AddCache;
import com.baizhi.annotation.ClearCache;
import com.baizhi.dao.AlbumDao;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Album;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class AlbumServiceImpl implements AlbumService {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private ChapterDao chapterDao;

    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    @AddCache
    public Map<String, Object> queryAll(Integer page, Integer rows) {
        Map<String, Object> map = new HashMap<String, Object>();
        //总条数
        Integer count = albumDao.selectCount();
        map.put("records", count);
        //总页数
        Integer pageCount = count % rows == 0 ? count / rows : count / rows + 1;
        map.put("total", pageCount);
        //起始条数
        page = (page - 1) * rows;
        //查到的数据
        List<Album> list = albumDao.selectAll(page, rows);
        map.put("rows", list);
        return map;
    }

    @Override
    @ClearCache
    public void insertAlbum(Album album) {
        albumDao.insertAlbum(album);
    }

    @Override
    @ClearCache
    public void updataAlbum(Album album) {
        albumDao.updataAlbum(album);
    }

    @Override
    @ClearCache
    public void deleteAlbum(String[] id) {
        albumDao.deleteAlbum(id);
        chapterDao.deleteAll(id);
    }

    @Override
    @AddCache
    @Transactional(propagation = Propagation.SUPPORTS)
    public List<Album> query() {
        return albumDao.select();
    }
}
