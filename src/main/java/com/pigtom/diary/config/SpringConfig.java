package com.pigtom.diary.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  * spring-boot有自己的一套web端拦截机制，若需要看到swagger发布的api文档界面，
 *  * 需要做一些特殊的配置，将springfox-swagger-ui包中的ui界面暴露给spring-boot资源环境。
 *  *
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/10 9:02 PM
 **/
@Configuration
public class SpringConfig implements WebMvcConfigurer {
    /**
     *
     * @param registry 跨域注册器
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
        .allowedMethods(HttpMethod.HEAD.name(),
                HttpMethod.GET.name(),
                HttpMethod.PUT.name(),
                HttpMethod.POST.name(),
                HttpMethod.DELETE.name(),
                HttpMethod.PATCH.name(),
                HttpMethod.OPTIONS.name(),
                HttpMethod.TRACE.name()
                ).exposedHeaders("Set-Cookie").allowCredentials(true).allowedOrigins("*");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/js/**").addResourceLocations("classpath:/js/");
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 配置FastJson为方式一
     * @return*/
//    @Override
//    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//        /*
//         * 1、需要先定义一个convert转换消息的对象 2、添加fastJson的配置信息，比如：是否要格式化返回json数据 3、在convert中添加配置信息
//         * 4、将convert添加到converters当中
//         *
//         */
//        // 1、需要先定义一个·convert转换消息的对象；
//        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
//        // 2、添加fastjson的配置信息，比如 是否要格式化返回json数据
//        FastJsonConfig fastJsonConfig = new FastJsonConfig();
//        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
//        // 3、在convert中添加配置信息.
//        fastConverter.setFastJsonConfig(fastJsonConfig);
//        // 4、将convert添加到converters当中.
//        converters.add(fastConverter);
//    }
}
