package com.pigtom.diary.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.pigtom.diary.common.PageList;
import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.mapper.SystemRoleMapper;
import com.pigtom.diary.model.query.SystemRoleQuery;
import com.pigtom.diary.service.SystemRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 * 系统角色表 服务实现类
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
@Service
public class SystemRoleServiceImpl extends ServiceImpl<SystemRoleMapper, SystemRole> implements SystemRoleService {

    @Resource
    private SystemRoleMapper systemRoleMapper;
    @Override
    public PageList<SystemRole> getList(SystemRoleQuery query) {
        Integer pageIndex = query.getPageIndex();
        Integer pageSize = query.getPageSize();
        PageHelper.startPage(pageIndex, pageSize);
        Page<SystemRole> page = systemRoleMapper.getList(query);
        return new PageList<>(page);
    }
}
