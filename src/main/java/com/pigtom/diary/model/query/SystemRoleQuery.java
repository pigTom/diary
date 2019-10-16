package com.pigtom.diary.model.query;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.pigtom.diary.common.Pager;
import lombok.Data;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/15 7:41 PM
 **/
@Data
public class SystemRoleQuery extends Pager {
    /**
     * 角色名称
     */
    private String name;
}
