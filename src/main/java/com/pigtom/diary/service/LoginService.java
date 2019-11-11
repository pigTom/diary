package com.pigtom.diary.service;

import com.pigtom.diary.model.bean.SystemUser;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/15 9:27 PM
 **/
public interface LoginService {
    SystemUser login(SystemUser user);
}
