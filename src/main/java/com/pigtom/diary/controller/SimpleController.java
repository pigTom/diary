package com.pigtom.diary.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/28 8:54 PM
 **/
@RestController
public class SimpleController {
        @RequestMapping("/hello")
    @ResponseBody
    String home() {
        return "Hello ,spring security!";
    }

    @GetMapping("/user")
    public String user() { return "Hello user";}
}
