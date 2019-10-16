package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/16 3:48 PM
 **/
@RestController
@RequestMapping("/api/v0.1/file")
public class FileDownloadController {
    @GetMapping("a.pdf")
    public ResponseEntity download(HttpServletRequest request, HttpServletResponse response) {
        String file = System.getProperty("user.dir");
        file = file.concat("/src/main/resources/jls8.pdf");
        response.setContentType("application/pdf");
        response.addHeader("Content-Disposition",
                "attachment; filename=secret.pdf");
        return ResponseEntity.success();
    }
}
