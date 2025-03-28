package com.przedtop.bank.system.configuration;

import com.przedtop.bank.system.services.handlers.RequestHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final RequestHandler preHandleWebInterceptorConfig;

    public WebConfig(RequestHandler preHandleWebInterceptorConfig) {
        this.preHandleWebInterceptorConfig = preHandleWebInterceptorConfig;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(preHandleWebInterceptorConfig)
                .addPathPatterns("/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/auth/**")
                .allowedOrigins("http://localhost:8081", "http://192.168.1.112:8081","127.0.0.1:8081")
                .allowedMethods("GET", "POST", "OPTIONS")
                .allowedHeaders("Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:8081", "http://192.168.1.112:8081","127.0.0.1:8081")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("Authorization", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600);
    }
}