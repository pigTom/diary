package com.pigtom.diary.config;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pigtom.diary.mapper.SystemRoleMapper;
import com.pigtom.diary.mapper.SystemUserMapper;
import com.pigtom.diary.model.bean.MyUserDetails;
import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.model.bean.SystemUser;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/10 4:08 PM
 **/
@Service("userDetailsService")
public class MyUserDetailService implements UserDetailsService {
    @Resource
    private SystemUserMapper systemUserMapper;
    @Resource
    private SystemRoleMapper systemRoleMapper;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SystemUser systemUser = new SystemUser();
        systemUser.setName(username);
        SystemUser user = systemUserMapper.
                selectOne(new QueryWrapper<>(systemUser));
        if (user == null) {
            throw new UsernameNotFoundException("Not found: " + username);
        }
        UserDetails userDetails = new MyUserDetails(user);
        SystemRole role = systemRoleMapper.selectById(user.getId());
        if (role != null) {
            ((MyUserDetails) userDetails).setAuthorities
                    (Collections.singletonList(new SimpleGrantedAuthority(role.getName())));
        }
        return userDetails;
    }
}
