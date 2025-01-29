package com.example.questionplatform.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.questionplatform.interceptor.AuthenticationInterceptor;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private AuthenticationInterceptor authenticationInterceptor;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true)
                .maxAge(3600);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authenticationInterceptor)
            .addPathPatterns(
                "/api/users/**",
                "/api/leaderboard/**",
                "/api/questions/**",
                "/api/dashboard/**",
                "/api/categories/**"
            )
            .excludePathPatterns(
                "/api/auth/**",  // Exclude auth endpoints
                "/api/users/register",  // Exclude registration if it exists
                "/api/users/login"      // Exclude login if it exists
            );
    }
} 