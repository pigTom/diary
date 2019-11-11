//package com.pigtom.diary.config;
//
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.context.annotation.Configuration;
//import springfox.documentation.service.ApiInfo;
//import springfox.documentation.service.Contact;
//import springfox.documentation.service.StringVendorExtension;
//import springfox.documentation.service.VendorExtension;
//import springfox.documentation.spi.DocumentationType;
//import springfox.documentation.spring.web.plugins.Docket;
//import springfox.documentation.swagger2.annotations.EnableSwagger2;
//
//import java.util.ArrayList;
//import java.util.Collections;
//
//import static springfox.documentation.builders.PathSelectors.regex;
//
///**
// * @author tangdunhong
// * @blame tangdunhong
// * @module diary
// * @since 2019/10/28 8:09 PM
// **/
//@Configuration
//@EnableSwagger2
//@ConditionalOnExpression("${swagger.enable:true}")
//public class SwaggerConfig {
//    public Docket swaggerSpringMvcPlugin() {
//        ApiInfo apiInfo = new ApiInfo(
//                "Spring Boot APIs",
//                "Spring Boot + Swagger2",
//                "0.0.1",
//                null,
//                new Contact("pigtom","diary","13393895196@163.com"),
//                "Author: pigtom",
//                "http:www.github.io/pigtom",
//                Collections.singleton(new StringVendorExtension("purpose", "practice"))
//        );
//        Docket docket = new Docket(DocumentationType.SWAGGER_2)
//                .select()
//                .paths(regex("api/.*"))
//                .build()
//                .apiInfo(apiInfo)
//                .useDefaultResponseMessages(false);
//        return docket;
//    }
//
//}
