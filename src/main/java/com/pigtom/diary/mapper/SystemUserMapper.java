package com.pigtom.diary.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.pagehelper.Page;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 系统用户表 Mapper 接口
 * </p>
 *
 * @author pigtom
 * @since 2019-10-01
 */
@Repository
public interface SystemUserMapper extends BaseMapper<SystemUser> {
    /**
     * 分页查询系统用户
     * @param query 查询对象
     * @return 分布列表
     */
    Page<SystemUser> pageList(@Param("query") SystemUserQuery query);
    
    
}
