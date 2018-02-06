package com.syx.comment.config.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author Msater Zg
 */
@Configuration
public class WebAppConfigurer extends WebMvcConfigurerAdapter {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 注册拦截器
        InterceptorRegistration interceptorRegistration = registry.addInterceptor(new ApiInterceptor());
        // 配置拦截的路径
        interceptorRegistration.addPathPatterns("/api*");
        // 配置不拦截的路径
        interceptorRegistration.excludePathPatterns("*.html");

        // 还可以在这里注册其它的拦截器
        /*registry.addInterceptor(new OtherInterceptor()).addPathPatterns("*//**");*/
    }
}
