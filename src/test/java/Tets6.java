import com.baizhi.App;
import com.baizhi.dao.PermDtoDao;
import com.baizhi.dao.RoleDtoDao;
import com.baizhi.dao.UserDao;
import com.baizhi.entity.PermDto;
import com.baizhi.entity.RoleDto;
import com.baizhi.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest(classes = App.class)
@RunWith(SpringRunner.class)
public class Tets6 {
    @Autowired
    private UserDao userDao;
    @Autowired
    private RoleDtoDao roleDtoDao;
    @Autowired
    private PermDtoDao permDtoDao;
    @Test
    public void test1(){
        User user = userDao.selectByname("15611313050");
        System.out.println(user);
    }

    @Test
    public void test2() {
        List<RoleDto> roleDtos = roleDtoDao.selectByid("2");
        for (RoleDto roleDto : roleDtos) {
            System.out.println(roleDto);
        }
    }

    @Test
    public void test3() {
        List<RoleDto> roleDtos = roleDtoDao.selectByid("2");
        for (RoleDto roleDto : roleDtos) {
            List<PermDto> permDtos = permDtoDao.selectByid(roleDto.getId());
            System.out.println(permDtos.size());
        }
    }
}
