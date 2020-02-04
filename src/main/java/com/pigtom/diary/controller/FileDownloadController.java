package com.pigtom.diary.controller;

import com.pigtom.diary.common.ResponseEntity;
import com.pigtom.diary.util.ExcelUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

import static com.pigtom.diary.config.RestApiUrl.BASE_API;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/16 3:48 PM
 **/
@RestController
@RequestMapping(BASE_API + "file")
public class FileDownloadController {
    @GetMapping("a.pdf")
    public ResponseEntity download(HttpServletRequest request, HttpServletResponse response) {

        try {
            request.setCharacterEncoding("UTF-8");
        } catch (UnsupportedEncodingException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }

        String file = System.getProperty("user.dir");
        file = file.concat("/src/main/resources/jls8.pdf");
        byte[] buffer = new byte[1024];
        FileInputStream fileInputStream = null;
        OutputStream os = null;
        try {
            long fileLength = new File(file).length();
            response.setHeader("Content-disposition", "attachment; filename=aa.pdf");
            response.setHeader("Content-Length", String.valueOf(fileLength));

            fileInputStream = new FileInputStream(new File(file));
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

    @GetMapping("exportExcel")
    ResponseEntity downloadExcel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filename = "testconfig-excel";
        ExcelUtil.handleResponse(request, response, filename);
        List<String> data = new ArrayList<>(10);

        data.add("AAA");
        data.add("BBB");
        data.add("CCC");
        data.add("hahahaha");

        ExcelUtil.writeDataToOutputStream(data, String.class, response.getOutputStream());


        return ResponseEntity.success();
    }

}
