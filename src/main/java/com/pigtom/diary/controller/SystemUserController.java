package com.pigtom.diary.controller;


import com.pigtom.diary.common.PageList;
import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;
import com.pigtom.diary.service.SystemUserService;
import com.pigtom.diary.util.ExcelUtil;
import io.swagger.annotations.Api;
import org.apache.tomcat.util.security.MD5Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

import static com.pigtom.diary.config.RestApiUrl.BASE_API;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 * @blame pigtom
 * @author pigtom
 * @since 2019-10-01
 */
@RestController
@Api(value = "SystemUserController", description = "系统用户管理")
@RequestMapping(BASE_API + "system_user")
public class SystemUserController {
    @Autowired
    private SystemUserService systemUserService;

    @PostMapping
    private ResponseEntity addSystemUser(@RequestBody SystemUser user) {
        Date now = new Date();
        user.setCreateTime(now);
        user.setCreateTime(now);
        user.setCreateId(1L);
        user.setUpdateId(1L);
        user.setUpdateTime(now);
        String md5Pass = MD5Encoder.encode(user.getAuthenticationString().getBytes());
        user.setAuthenticationString(md5Pass);
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

    /**
     * export excel
     */
    @GetMapping("export-excel")
    public ResponseEntity exportExcel(
                                      HttpServletRequest request,
                                      HttpServletResponse response)
            throws Exception {
        ExcelUtil.handleResponse(request, response, "users");
        List<SystemUser> list = systemUserService.getList(new SystemUserQuery()).getList();
        ExcelUtil.writeDataToOutputStream(list, SystemUser.class, response.getOutputStream());
        return ResponseEntity.success();
    }
}
