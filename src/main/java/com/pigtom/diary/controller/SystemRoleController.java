package com.pigtom.diary.controller;


import com.pigtom.diary.common.PageList;
import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.model.query.SystemRoleQuery;
import com.pigtom.diary.service.SystemRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

import static com.pigtom.diary.config.RestApiUrl.BASE_API;

/**
 * <p>
 * 系统角色表 前端控制器
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
@Api(value = "SystemRoleController", description = "用户角色接口")
@RestController
@RequestMapping(BASE_API + "system_role")
public class SystemRoleController {

    @Autowired
    private SystemRoleService systemRoleService;

    @ApiOperation("新增角色")
    @PostMapping
    public ResponseEntity add(@RequestBody SystemRole role) {

        role.setCreateTime(new Date());
        role.setCreateId(0L);
        role.setUpdateTime(new Date());
        systemRoleService.save(role);
        return ResponseEntity.success();
    }

    @ApiOperation("删除角色")
    @DeleteMapping("{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        systemRoleService.removeById(id);
        return ResponseEntity.success();
    }
    @PutMapping
    public ResponseEntity update(@RequestBody SystemRole role) {
        role.setUpdateTime(new Date());
        systemRoleService.updateById(role);
        return ResponseEntity.success();
    }

    @PostMapping("getList")
    public ResponseEntity<PageList<SystemRole>> getList(@RequestBody SystemRoleQuery query) {
        PageList<SystemRole> pageList = systemRoleService.getList(query);
        return ResponseEntity.success(pageList);
    }
}
