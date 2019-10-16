package com.pigtom.diary.mapper;

import com.github.pagehelper.Page;
import com.pigtom.diary.model.bean.SystemRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pigtom.diary.model.query.SystemRoleQuery;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
    Page<SystemRole> getList(@Param("query") SystemRoleQuery query);
}
