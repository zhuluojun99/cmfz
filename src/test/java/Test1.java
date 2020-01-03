import com.baizhi.App;
import com.baizhi.dao.AdminDao;
import com.baizhi.dao.BannerDao;
import com.baizhi.entity.Admin;
import com.baizhi.entity.Banner;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class Test1 {
    @Autowired
    private AdminDao adminDao;
    @Autowired
    private BannerDao bannerDao;
    @Test
    public void test1(){
        Admin admin = adminDao.selectbyname("admin");
        System.out.println(admin);
    }

    @Test
    public void test2(){
        List<Banner> banners = bannerDao.selectAll(0,3);
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }

    @Test
    public void test3(){
        List<Banner> banners = bannerDao.selectAllbyStatus();
        for (Banner banner : banners) {
            System.out.println(banner);
        }
    }

    @Test
    public void test4(){
        Banner banner = new Banner();
        banner.setId("9");
        banner.setTitle("图片");
        banner.setCreate_date(new Date());
        banner.setStatus("激活");
        banner.setImg("xxxxxx");
        bannerDao.InsertOne(banner);
    }

    @Test
    public void test5(){
        List<Integer> montnDto = bannerDao.selectByday();
        System.out.println(montnDto.toString());
    }
}
