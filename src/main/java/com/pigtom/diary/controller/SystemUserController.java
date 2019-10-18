package com.pigtom.diary.controller;


import com.pigtom.diary.common.PageList;
import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;
import com.pigtom.diary.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
@RestController
@RequestMapping("/api/v0.1/system-user")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    @PostMapping
    private ResponseEntity addSystemUser(@RequestBody SystemUser user) {

        user.setCreateTime(LocalDateTime.now());
        user.setCreateId(1L);
        user.setUpdateId(1L);
        user.setUpdateTime(LocalDateTime.now());
        systemUserService.save(user);
        return ResponseEntity.success();
    }

    @DeleteMapping("{id}")
    private ResponseEntity deleteSystemUser(@PathVariable Long id) {
        systemUserService.removeById(id);
        return ResponseEntity.success();
    }
    @PutMapping
    private ResponseEntity updateSystemUser(@RequestBody SystemUser user) {
        systemUserService.updateById(user);
        return ResponseEntity.success();
    }

    @PostMapping("getList")
    private ResponseEntity<PageList<SystemUser>> getSystemUserList(@RequestBody SystemUserQuery query) {
        PageList<SystemUser> pageList = systemUserService.getList(query);
        return ResponseEntity.success(pageList);
    }

    @GetMapping("{id}")
    private ResponseEntity<SystemUser> get(@PathVariable Long id) {
        SystemUser user = systemUserService.getById(id);
        return ResponseEntity.success(user);
    }
}
