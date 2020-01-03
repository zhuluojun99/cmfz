import com.baizhi.App;
import com.baizhi.dao.ChapterDao;
import com.baizhi.entity.Chapter;
import com.baizhi.service.ChapterService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Test4 {
    @Autowired
    private ChapterDao chapterDao;
    @Autowired
    private ChapterService chapterService;
    @Test
    public void test1(){
        Map<String, Object> stringObjectMap = chapterService.queryAll("3", 1, 2);
        System.out.println(stringObjectMap.size());
    }
    @Test
    public void test2(){
        Integer chapters = chapterDao.selectCount("3");
            System.out.println(chapters);
    }
    @Test
    public void test3(){
        Chapter ch = new Chapter();
        ch.setId("22");
        ch.setTitle("xxxxx");
        ch.setAlbum_id("3");
        ch.setSrc("xxx.mp3");
        ch.setStatus("激活");
        ch.setCreate_date(new Date());
        chapterDao.InsertOne(ch);
    }


}
