import com.baizhi.App;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Tets6 {
    @Autowired
    private UserDao userDao;
    @Test
    public void test1(){
        User user = userDao.selectByname("15611313050");
        System.out.println(user);
    }
}
