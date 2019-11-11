package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/15 9:23 PM
 **/
@Api(tags = "用户控制器")
@RestController("/api/v0.1/login")
public class LoginController {
    @ApiOperation(value = "登录")
    @PostMapping
    public ResponseEntity login(@RequestBody SystemUser user) {

        return ResponseEntity.success();
    }

    @ApiOperation(value = "客人登录")
    @GetMapping
    public ResponseEntity login() {
        return ResponseEntity.success();
    }
}
