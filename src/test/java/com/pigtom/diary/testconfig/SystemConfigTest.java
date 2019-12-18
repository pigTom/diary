package com.pigtom.diary.testconfig;

import com.pigtom.diary.util.SystemConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module config
 * @since 2019/12/11 2:09 PM
 **/
@SpringBootApplication
@RunWith(SpringRunner.class)
public class SystemConfigTest {
    private SystemConfig systemConfig = SystemConfig.getSystemConfig();

    @Test
    public void getConfigString() {
        String key = "2.person.name";
        String value = (String) systemConfig.get(key);
        System.out.println(value);
    }
}
