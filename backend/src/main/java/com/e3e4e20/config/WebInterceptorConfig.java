package com.e3e4e20.config;

import com.e3e4e20.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

/*
Filename: WebInterceptorConfig
Created: 2023年06月02日 10时44分15秒 星期五
Author: 天龙梦雪
*/
@Configuration
public class WebInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        List<String> excludePathList = new ArrayList<>();
        excludePathList.add("/login/check");
        List<String> swaggerExcludes = new ArrayList<>();
        swaggerExcludes.add("/swagger-ui.html/**");
        swaggerExcludes.add("/swagger-resources/**");
        swaggerExcludes.add("/webjars/**");
        swaggerExcludes.add("/v2/**");
        swaggerExcludes.add("/");
        swaggerExcludes.add("/error");
        swaggerExcludes.add("");
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns(excludePathList)
                .excludePathPatterns(swaggerExcludes);
        WebMvcConfigurer.super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        WebMvcConfigurer.super.addResourceHandlers(registry);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("404");
        registry.addViewController("/error").setViewName("error");
        WebMvcConfigurer.super.addViewControllers(registry);
    }
}
