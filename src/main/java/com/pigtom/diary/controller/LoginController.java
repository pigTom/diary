package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.model.bean.SystemRole;
import com.pigtom.diary.model.bean.SystemUser;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/15 9:23 PM
 **/
@RestController("/api/v0.1/")
public class LoginController {
    @PostMapping
    public ResponseEntity login(@RequestBody SystemUser user) {
        return ResponseEntity.success();
    }
}
