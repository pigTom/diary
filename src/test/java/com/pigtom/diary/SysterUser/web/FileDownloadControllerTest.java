package com.pigtom.diary.SysterUser.web;

import com.pigtom.diary.controller.FileDownloadController;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

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


//    @Test
//    public void testPath() throws Exception{
//        System.out.println(System.getProperty("user.dir"));
//        String file = System.getProperty("user.dir");
//        file = file.concat("/application.yml");
//        FileInputStream inputStream = new FileInputStream(new File(file));
//        BufferedInputStream bi = new BufferedInputStream(inputStream);
//        FileReader rf = new FileReader(file);
//        char[] chars = new char[2049];
//        int i = rf.read(chars);
//        System.out.println(chars);
//    }
}
