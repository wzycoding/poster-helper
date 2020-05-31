package com.wzy.config;

import com.wzy.interceptor.LoginHandlerInterceptor;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {

    @Bean
    public LoginHandlerInterceptor getLoginHandlerInterceptor() {
        return new LoginHandlerInterceptor();
    }


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getLoginHandlerInterceptor()).addPathPatterns("/**")
                .excludePathPatterns("/", "/api/v1.0/user/verifyCode", "/api/v1.0/user/login", "/api/v1.0/user/register");
    }

    // 实现静态资源的映射
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/META-INF/resources/")  // 映射swagger2
                .addResourceLocations("file:/Users/wzy/workspaces/images/");  // 映射本地静态资源
//                .addResourceLocations("file:/home/workspaces/images/");  // 映射本地静态资源
    }
    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }
}