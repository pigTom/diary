package com.pigtom.diary.model.test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module test
 * @since 2020/1/5 1:23 PM
 **/
@Component()
public class SpringTest {
    SpringTest(){}
    @Value("${test.name}")
    public String name;

    public String getName() {
        System.out.println("get name");
        return name;
    }

    public void setName(String name) {
        System.out.println("set name " + name);
        this.name = name;
    }
}
