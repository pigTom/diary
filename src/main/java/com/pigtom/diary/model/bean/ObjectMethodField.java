package com.pigtom.diary.model.bean;

import lombok.Data;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module config
 * @since 2020/1/7 10:56 PM
 **/
@Data
public class ObjectMethodField {
    String valueName;

    private Field field;

    private Method method;
}
