package com.pigtom.diary.systemtest;

import org.junit.Test;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/27 2:22 PM
 **/
public class TestSpringBoot {
    @Test
    public void test() {
        String url = null;
        System.out.println(String.format("url %s", url));
    }


    @Test
    public void testSub() {
        String aa = "2341";
        System.out.println(aa.substring(0, 0));
    }

    @Test
    public void testRegex() {
        String s = "fafaf fafda faa";
        String s1 = "fafaf fafda faa{";
        String s2 = "fafaf fafda { faa}";

        String regex = "[{}]";
        System.out.println(s.replaceAll(regex," tang"));
        System.out.println(s1.replaceAll(regex, " tang"));
        System.out.println(s2.replaceAll(regex, " tang"));
    }

    @Test
    public void testSlash() {
        String text = "location ~ .*AAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB.(js|css)?$";

        System.out.println(text.replaceAll("AAAAAAAAAAAAAAAAAAAAAAABBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB",
                "\\\\"));
    }
}
