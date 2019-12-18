package com.pigtom.diary.testbean;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/24 9:46 PM
 **/
@SpringBootTest
@RunWith(SpringRunner.class)
public class User {
    public User() {
        name = "haha";
    }
    private String name;
    private String value;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }

    @Test
    public void testAnnotation() {
        System.out.println("haha");
        System.out.println(value);
    }
}
