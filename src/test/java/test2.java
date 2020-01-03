import com.baizhi.App;
import com.baizhi.entity.Banner;
import com.baizhi.service.BannerService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class test2 {
    @Autowired
    private BannerService bannerService;

    @Test
    public void test1(){
        Map<String, Object> stringObjectMap = bannerService.queryAll(1, 3);
        for (Map.Entry<String, Object> stringObjectEntry : stringObjectMap.entrySet()) {
            System.out.println(stringObjectEntry.getKey()+stringObjectEntry.getValue());
        }
    }

    @Test
    public void test2(){
        List<Banner> banners = bannerService.queryAllbyStatus();
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }

}
