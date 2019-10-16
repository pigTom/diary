package com.pigtom.diary.SysterUser.web;

import com.pigtom.diary.controller.FileDownloadController;
import com.pigtom.diary.model.bean.SystemUser;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.io.File;
import java.net.URI;
import java.nio.file.Path;
import java.util.Map;
import java.util.Properties;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/16 7:05 PM
 **/
public class FileDownloadControllerTest {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;
    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.standaloneSetup(new FileDownloadController()).build();  //构造MockMvc
    }

    @Test
    public void downLoad() throws Exception{
        mockMvc.perform(MockMvcRequestBuilders.get("/api/v0.1/file/a.pdf")).andReturn();
    }

    @Test
    public void testPath() {
        System.out.println(System.getProperty("user.dir"));
        String file = System.getProperty("user.dir");
        file = file.concat("/src/main/resources/jls8.pdf");
    }
}
