package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
    @GetMapping("a.pdf")
    public ResponseEntity download(HttpServletRequest request, HttpServletResponse response) {

        response.setContentType("text/html;charset=utf-8;application/x-msdownload;");
        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String file = System.getProperty("user.dir");
        file = file.concat("/src/main/resources/jls8.pdf");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "aa");
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = null;
        BufferedInputStream reader = null;
        OutputStream os = null;
        try {
            long fileLength = new File(file).length();
            response.setHeader("Content-disposition", "attachment; filename=aa");
            response.setHeader("Content-Length", String.valueOf(fileLength));

            fileInputStream = new FileInputStream(new File(file));
            reader = new BufferedInputStream(fileInputStream);
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
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        return ResponseEntity.success();
    }
}
