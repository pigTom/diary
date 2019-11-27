package com.pigtom.diary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring-boot有自己的一套web端拦截机制，若需要看到swagger发布的api文档界面，
 * 需要做一些特殊的配置，将springfox-swagger-ui包中的ui界面暴露给spring-boot资源环境。
 *
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/11/24 3:20 PM
 **/
@Configuration
public class WebMvcController implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}
