package com.pigtom.diary.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pigtom.diary.common.PageList;
import com.pigtom.diary.mapper.SystemUserMapper;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;
import com.pigtom.diary.service.SystemUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * <p>
 * 系统用户表 服务实现类
 * </p>
 *
 * @author pigtom
 * @since 2019-10-01
 */
@Service
public class SystemUserServiceImpl extends ServiceImpl<SystemUserMapper, SystemUser> implements SystemUserService {
    @Resource
    private SystemUserMapper systemUserMapper;

    @Override
    public void add(SystemUser user) {
        Date now = new Date();
        user.setCreateTime(now);
        user.setCreateTime(now);
        user.setCreateId(1L);
        user.setUpdateId(1L);
        user.setUpdateTime(now);
        user.setAuthenticationString(new BCryptPasswordEncoder().encode(user.getAuthenticationString()));
    }

    @Override
    public PageList<SystemUser> getList(SystemUserQuery query) {
        Integer pageNum = query.getPageIndex();
        Integer pageSize = query.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        Page<SystemUser> page = systemUserMapper.pageList(query);
        System.out.println(page.getTotal());
        return new PageList<>(page);
    }
}
