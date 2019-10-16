package com.pigtom.diary.common;

import lombok.Data;

import java.io.Serializable;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/9 8:31 PM
 **/
@Data
public class Pager implements Serializable {
    private static long serialVersionUID = 1L;

    /**
     * 当前的页面数
     */
    private Integer pageIndex = 1;

    /**
     * 每页大小
     */
    private Integer pageSize = 20;

    /**
     * 总记录数
     */
    private Long total;

}
