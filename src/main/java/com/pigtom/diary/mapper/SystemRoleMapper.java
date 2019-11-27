package com.pigtom.diary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.model.query.SystemRoleQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统角色表 Mapper 接口
 * </p>
 *
 * @author pigtom
 * @blame pigtom
 * @since 2019-10-01
 */
@Repository
public interface SystemRoleMapper extends BaseMapper<SystemRole> {
    /**
     * 分布查询角色列表
     * @param query 角色查询类
     * @return 角色列表
     */
    Page<SystemRole> getList(@Param("query") SystemRoleQuery query);
}
