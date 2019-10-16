package com.pigtom.diary.SysterUser;

import com.pigtom.diary.common.PageList;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;
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
 * @since 2019/10/10 5:12 PM
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class SystemUserTest {
    @Autowired
    private SystemUserService systemUserService;

    @Test
    public void testPageList() {
        SystemUserQuery query = new SystemUserQuery();
        PageList<SystemUser> pageList = systemUserService.getList(query);
        System.out.println(pageList);
    }
}
