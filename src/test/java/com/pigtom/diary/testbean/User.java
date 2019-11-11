package com.pigtom.diary.testbean;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/24 9:46 PM
 **/
public class User {
    public User() {
        name = "haha";
    }
    private String name;

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
