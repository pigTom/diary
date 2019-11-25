package com.pigtom.diary.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.StringVendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

/**
 * @author tangdunhong
 * @blame tangdunhong
 * @module diary
 * @since 2019/10/28 8:09 PM
 **/
@Configuration
@EnableSwagger2
@ConditionalOnExpression("${swagger.enable:true}")
public class SwaggerConfig {
    @Bean
    public Docket swaggerSpringMvcPlugin() {
        ApiInfo apiInfo = new ApiInfo(
                "Spring Boot APIs",
                "Spring Boot + Swagger2",
                "0.0.1",
                null,
                new Contact("pigtom","diary","13393895196@163.com"),
                "Author: pigtom",
                "http:www.github.io/pigtom",
                Collections.singleton(new StringVendorExtension("purpose", "practice"))
        );

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo)
                .select()
                // api
                .apis(RequestHandlerSelectors.basePackage("com.pigtom.diary.controller"))
                .paths(PathSelectors.any())
                .build()
                .useDefaultResponseMessages(false);
    }

}
