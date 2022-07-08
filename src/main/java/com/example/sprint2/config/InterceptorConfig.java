package com.example.sprint2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author ZWForum
 * @description
 * @date 2022/7/7
 */
@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JwtInterceptorConfig())
                //拦截路径
                .addPathPatterns("/**")
                //开放路径
                .excludePathPatterns("/error")
                .excludePathPatterns("/users/login");
    }
}
