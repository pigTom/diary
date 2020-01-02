package com.pigtom.diary.systemtest;

import com.pigtom.diary.testbean.User;
import org.junit.Test;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module test
 * @since 2019/12/23 3:56 PM
 **/
public class TestDateTime {
    @Test
    public void testDateFormate() {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("MM月dd日HH时00分");
        System.out.println(format.format(date));
    }

    @Test
    public void simpleTest() throws Exception{
        User user = new User();
        Printor printor = new Printor();
        String name = "tang";
        Method method = printor.getClass().getMethod("print", name.getClass());
        method.invoke(printor, name);
//        user.testAnnotation();
    }



    class Printor{
        public void print(String name) {
            System.out.println(name);
        }
    }
}
