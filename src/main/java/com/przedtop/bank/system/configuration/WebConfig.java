package com.przedtop.bank.system.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final PreHandlerConfig preHandleWebInterceptorConfig;

    public WebConfig(PreHandlerConfig preHandleWebInterceptorConfig) {
        this.preHandleWebInterceptorConfig = preHandleWebInterceptorConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(preHandleWebInterceptorConfig)
                .addPathPatterns("/**");
    }
}
