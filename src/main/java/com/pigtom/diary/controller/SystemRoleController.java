package com.pigtom.diary.controller;


import com.pigtom.diary.common.PageList;
import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.model.query.SystemRoleQuery;
import com.pigtom.diary.service.SystemRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
@RestController
@RequestMapping("/api/v0.1/system-role")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @PostMapping
    public ResponseEntity add(@RequestBody SystemRole role) {

        role.setCreatedTime(LocalDateTime.now());
        role.setCreatorId(0L);
        role.setUpdatedTime(LocalDateTime.now());
        systemRoleService.save(role);
        return ResponseEntity.success();
    }

    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        systemRoleService.removeById(id);
        return ResponseEntity.success();
    }
    @PutMapping
    public ResponseEntity update(@RequestBody SystemRole role) {
        role.setUpdatedTime(LocalDateTime.now());
        systemRoleService.updateById(role);
        return ResponseEntity.success();
    }

    @PostMapping("getList")
    public ResponseEntity<PageList<SystemRole>> getList(@RequestBody SystemRoleQuery query) {
        PageList<SystemRole> pageList = systemRoleService.getList(query);
        return ResponseEntity.success(pageList);
    }
}
