package com.capg.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Config (Security Configuration Class)
 * 
 * This class is used to configure JWT Filter in the application.
 * 
 * Responsibilities:
 * - Register JwtFilter
 * - Apply filter on specific URL patterns
 * - Secure APIs using token validation
 * 
 * In this configuration:
 * - JWT filter is applied on all "/friends/*" APIs
 * - Login or public APIs can be excluded
 */
@Configuration
public class Config {

    /**
     * Register JwtFilter using FilterRegistrationBean
     * 
     * @return FilterRegistrationBean object with JwtFilter configured
     */
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter() {

        FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();

        // Set custom JWT filter
        bean.setFilter(new JwtFilter());

        /**
         * Apply JWT filter only on secured APIs
         * 
         * Here:
         * - All "/friends/*" endpoints are secured
         * - Login APIs are excluded
         */
        bean.addUrlPatterns("/friends/*");

        return bean;
    }
}