package com.baizhi.service.impl;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import com.baizhi.service.UserService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;
    @Override
    @Transactional(propagation = Propagation.SUPPORTS)
    public Map<String,Object> selectByLogin(String phone, String password, String code, HttpSession session) {
        User user = userDao.selectByname(phone);
        String code1 = (String) session.getAttribute("code");
        Map<String,Object> map = new HashMap<>();
        if(code1.equals(code)){
            if(user!=null){
                if(user.getPassword().equals(password)){
                    map.put("password", user.getSalt());
                    map.put("farmington",user.getDharma());
                    map.put("uid",user.getId());
                    map.put("nickname",user.getName());
                    map.put("gender",user.getSex());
                    map.put("photo",user.getHead_img());
                    map.put("location",user.getAddress());
                    map.put("province",user.getAddress());
                    map.put("city",user.getAddress());
                    map.put("description",user.getSign());
                    map.put("phone ",user.getPhone_number());
                }else {
                    map.put("error","-200");
                    map.put("error_msg","密码错误");
                }
            }
        }
        return map;
    }

    @Override
    public Map<String, Object> insert(User user) {
        User user1 = userDao.selectByname(user.getPhone_number());
        Map<String,Object> map = new HashMap<>();
        if(user1!=null){
            map.put("errno","-200");
            map.put("error_msg","");
        }else {
            user.setLast_date(new Date());
            user.setCreate_date(new Date());
            String salt = UUID.randomUUID().toString().replace("-", "");
            user.setId(DigestUtils.md5Hex(user.getPassword()+salt));
            user.setSalt(salt);
            String id = UUID.randomUUID().toString().replace("-", "");
            user.setId(id);
            userDao.insert(user);
            map.put("password",user.getPassword());
            map.put("uid",user.getId());
            map.put("phone ",user.getPhone_number());
        }
        return map;
    }
}
