package com.pigtom.diary;

import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.util.BeanUtil;
import org.junit.Test;

import java.io.*;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/1 10:19 AM
 **/
public class test {
    @Test
    public void testWriteAFile() {
        String filename = System.getProperty("user.dir");
        File dir = new File(filename + "/b/c");
        if (!dir.mkdirs()) {
            System.out.println("make dir fail");
            return;
        }
        try {
            filename = dir.getCanonicalPath() + "/b.txt";
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            OutputStream outputStream = new FileOutputStream(filename);
            try {
                outputStream.write("hello world".getBytes("utf-8"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testMapToOBject() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "tang");
        map.put("description", "test");
        try {
            SystemRole role = BeanUtil.mapToEntity(map, SystemRole.class);
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("hahaha");
    }

    @Test
    public void testMd5() {
        String password = "123456";
        byte[] ch = password.getBytes();
        System.out.println(Arrays.toString(ch));
//        String md5 = MD5
//        System.out.println(md5);
        System.out.println(Long.MAX_VALUE);

    }

    @Test
    public void testGroupBy() {
        List<SystemUser> list = new ArrayList<>();
        SystemUser user = new SystemUser();
        user.setName("000");
        user.setId(0L);

        SystemUser user1 = new SystemUser();
        user1.setName("100");
        user1.setId(1L);

        SystemUser user2 = new SystemUser();
        user2.setName("200");
        user2.setId(2L);

        SystemUser user3 = new SystemUser();
        user3.setName("300");
        user3.setId(3L);

        list.add(user);
        list.add(user1);
        list.add(user2);
        list.add(user3);
//        Function function = (a) -> {
//            List<Long> list1 = new ArrayList<>();
//            list.add
//        };

        Map<Long, String> map = list.stream().collect(Collectors.toMap(SystemUser::getId, SystemUser::getName));
        Map<Long, List<SystemUser>> strmap =
                list.stream().collect(Collectors.groupingBy(SystemUser::getId));

        System.out.println(strmap);
    }


    @Test
    public void testLong() {
        Long id = 41414141L;
        System.out.println(id);
    }

    public <T> void print(List<T> data, Class<T> tClass) {
        try {
            T t = tClass.newInstance();
            data.add(t);
            data.add(t);
            data.add(t);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        System.out.println(data);
    }

    @Test
    public void testGenericType() throws Exception{
        A.desc();
    }


    @Test
    public void testRound() {
        BigDecimal bd = new BigDecimal("333.15");
        bd = bd.setScale(-1, BigDecimal.ROUND_HALF_UP);
        System.out.println(bd);
    }

    private void testDelete(List<String> list) {
        Iterator<String> iterator = list.iterator();
        String next = iterator.next();
        next = iterator.next();
        iterator.remove();
    }
    @Test
    public void testListDelete() {
        List<String> list = new ArrayList<>();
        list.add("HHHAAA ");
        list.add("2131");
        list.add("213100000");
        testDelete(list);
        System.out.println("list = " + list);;
    }
}

enum A{
     a(1);
     A(int i){};
    int value;

    public void setValue(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

   public static void desc() {
       System.out.println("Hello World");
   }
}