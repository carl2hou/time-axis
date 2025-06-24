package com.time.axis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author carl
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器并配置拦截规则
        registry.addInterceptor(loginInterceptor)
                .addPathPatterns("/api/**")   // 拦截所有API路径
                .excludePathPatterns(          //排除不需要拦截的路径
                    "/api/user/token"
                );
    }
}