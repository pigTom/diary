package com.pigtom.diary.model.query;

import com.pigtom.diary.common.Pager;
import lombok.Data;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/9 8:38 PM
 **/
@Data
public class SystemUserQuery extends Pager {
    /**
     * 用户名
     */
    private String name;

}
