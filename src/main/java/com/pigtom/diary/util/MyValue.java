package com.pigtom.diary.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/12/11 10:08 AM
 **/
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface MyValue {

}
