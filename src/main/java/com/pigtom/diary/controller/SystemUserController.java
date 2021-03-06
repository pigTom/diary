package com.pigtom.diary.controller;


import com.pigtom.diary.common.PageList;
import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemUser;
import com.pigtom.diary.model.query.SystemUserQuery;
import com.pigtom.diary.service.SystemUserService;
import com.pigtom.diary.util.ExcelUtil;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private SystemUserService systemUserService;

    @PostMapping
    public ResponseEntity addSystemUser(@RequestBody SystemUser user) {
       systemUserService.save(user);
        return ResponseEntity.success();
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteSystemUser(@PathVariable Long id) {
        systemUserService.removeById(id);
        return ResponseEntity.success();
    }
    @PutMapping
    public ResponseEntity updateSystemUser(@RequestBody SystemUser user) {
        systemUserService.updateById(user);
        return ResponseEntity.success();
    }

    @PostMapping("getList")
    public ResponseEntity<PageList<SystemUser>> getSystemUserList(@RequestBody SystemUserQuery query) {
        PageList<SystemUser> pageList = systemUserService.getList(query);
        return ResponseEntity.success(pageList);
    }

    @GetMapping("{id}")
    public ResponseEntity<SystemUser> get(@PathVariable Long id) {
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
