package com.pigtom.diary.SysterUser;

import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.service.SystemUserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/12/1 3:57 PM
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class UserTest {
    @Autowired
    private SystemUserService systemUserService;

    @Test
    public void addUser() {
        String username = "tang";
        String password = "123456";
        SystemUser user = new SystemUser();
        user.setName(username);
        user.setAuthenticationString(password);
        systemUserService.add(user);
        System.out.println(user.getAuthenticationString());
    }
}
