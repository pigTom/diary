package com.pigtom.diary.util;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/17 5:02 PM
 **/
public class BeanUtil {
    @SuppressWarnings("unchecked")
    public static <T> T mapToEntity(Map<String, Object> map, Class<T> tClass)
    throws InstantiationException, IllegalAccessException{
        T t = (T) tClass.newInstance();
        for (Field field : tClass.getDeclaredFields()) {
            if (map.containsKey(field.getName())) {
                boolean flag = field.isAccessible();
                field.setAccessible(true);
                field.set(t, map.get(field.getName()));
                field.setAccessible(flag);
            }
        }
        return t;
    }
}
