import com.baizhi.App;
import com.baizhi.dao.AlbumDao;
import com.baizhi.service.AlbumService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Map;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Test3 {
    @Autowired
    private AlbumDao albumDao;
    @Autowired
    private AlbumService albumService;
    @Test
    public void test(){
        Map<String, Object> stringObjectMap = albumService.queryAll(1, 4);
        System.out.println(stringObjectMap.size());
    }

    @Test
    public void test1(){
       albumDao.updataAddCount("34488cb1ff304505859db511cefc85c5");
    }
    @Test
    public void test2(){
        albumDao.updataDeleteCount("34488cb1ff304505859db511cefc85c5",1);
    }
}
