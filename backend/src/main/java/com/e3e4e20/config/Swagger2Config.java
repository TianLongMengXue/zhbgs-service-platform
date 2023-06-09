package com.e3e4e20.config;

import com.e3e4e20.constant.ProjectDefaultConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/*
Filename: Swagger2Config
Created: 2023年04月07日 19时00分13秒 星期五
Author: 天龙梦雪
*/
@Configuration // spring 配置类注解
@EnableSwagger2 // 启用 Swagger2 功能
public class Swagger2Config{
    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage(ProjectDefaultConfig.SWAGGER_API_BASE_PACKAGE)) // com.e3e4e20 包下面所有 api 都交付给 swagger2 管理
                .paths(PathSelectors.any())
                .build();
    }
    private ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title(ProjectDefaultConfig.PROJECT_NAME) // API 文档标题
                .description(ProjectDefaultConfig.SWAGGER_API_DESCRIPTION) // API 文件的简要描述
                .version(ProjectDefaultConfig.SWAGGER_API_VERSION) // API 文档的版本号
                .build();
    }
}