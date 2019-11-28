package com.pigtom.diary;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/9/30 8:19 PM
 **/

@SpringBootApplication

@RestController
@RefreshScope
public class App {
    public static void main1(String[] args) {
        SpringApplication.run(App.class, args);
    }


    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }


    @Value("${server.port}")
    private int port;

//    public void setPort(int port){
//        this.port=port;
//    }

    @RequestMapping("/port")
    public int port(){
        return port;
    }
}
