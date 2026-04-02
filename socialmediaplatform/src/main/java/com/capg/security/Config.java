package com.capg.security;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {

	@Bean
	public FilterRegistrationBean<JwtFilter> jwtFilter() {

		FilterRegistrationBean<JwtFilter> bean = new FilterRegistrationBean<>();
		bean.setFilter(new JwtFilter());

		// Apply on all APIs except login
		bean.addUrlPatterns("/friends/*");

		return bean;
	}
}
