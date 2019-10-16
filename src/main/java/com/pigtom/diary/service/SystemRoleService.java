package com.pigtom.diary.service;

import com.pigtom.diary.common.PageList;
import com.pigtom.diary.model.bean.SystemRole;
import com.baomidou.mybatisplus.extension.service.IService;
import com.pigtom.diary.model.query.SystemRoleQuery;

/**
 * <p>
 * 系统角色表 服务类
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
public interface SystemRoleService extends IService<SystemRole> {
    /**
     * 分=列查询
     * @param query 查询类
     * @return 分页列表
     */
    PageList<SystemRole> getList(SystemRoleQuery query);

}
