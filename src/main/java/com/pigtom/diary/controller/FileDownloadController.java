package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/16 3:48 PM
 **/
@RestController
@RequestMapping("/api/v0.1/file")
public class FileDownloadController {
    @GetMapping("{filename}")
    public ResponseEntity download(@PathVariable String filename, HttpServletRequest request, HttpServletResponse response)
            throws Exception{

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        File file = new File(filename);

        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = null;
        OutputStream os = null;
        try {
            long fileLength = file.length();
            response.setHeader("Content-disposition", "attachment; filename="+filename);
            response.setHeader("Content-Length", String.valueOf(fileLength));

            fileInputStream = new FileInputStream(file);
            os = response.getOutputStream();
            int i = fileInputStream.read(buffer);
            while (i != -1) {
                os.write(buffer, 0, i);
                i = fileInputStream.read(buffer);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }  finally {
            try {
                if (os != null) {
                    os.close();
                }
                if (fileInputStream != null) {
                    fileInputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return ResponseEntity.success();
    }
}
