package com.pigtom.diary.util;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/17 5:02 PM
 **/

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExcelColumn {
    /**
     * 对应excel列的标题
     * @return excel列标题
     */
    String value() default "";

    int digitLen() default 0;

    int columnIndex();
    int columnWidth() default 3500;
    int rowHeight() default 2 * 256;

}
