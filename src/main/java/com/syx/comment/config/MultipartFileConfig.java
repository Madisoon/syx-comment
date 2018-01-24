package com.syx.comment.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.MultipartConfigElement;

/**
 * 描述:
 * 文件上传大小的限制类
 *
 * @author Msater Zg
 * @create 2018-01-05 13:52
 */

@Configuration
public class MultipartFileConfig {
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("10240KB");
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }
}
