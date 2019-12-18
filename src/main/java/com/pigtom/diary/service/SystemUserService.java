package com.pigtom.diary.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pigtom.diary.common.PageList;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author pigtom
 * @since 2019-10-01
 */
public interface SystemUserService extends IService<SystemUser> {

    void add(SystemUser user);

    PageList<SystemUser> getList(SystemUserQuery query);
}
