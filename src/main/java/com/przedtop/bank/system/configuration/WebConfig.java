package com.przedtop.bank.system.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PreHandleWebInceptorConfig preHandleWebInterceptorConfig;

    public WebConfig(PreHandleWebInceptorConfig preHandleWebInterceptorConfig) {
        this.preHandleWebInterceptorConfig = preHandleWebInterceptorConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(preHandleWebInterceptorConfig)
                .addPathPatterns("/**");
    }
}
