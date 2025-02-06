package com.example.schedulerv2.config;

import com.example.schedulerv2.filter.JwtAuthFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<JwtAuthFilter> jwtAuthFilter(){
        FilterRegistrationBean<JwtAuthFilter> filterBean = new FilterRegistrationBean<>();
        filterBean.setFilter(new JwtAuthFilter());
        filterBean.addUrlPatterns("/*"); // 모든 경로에 적용
        filterBean.setOrder(1);
        return filterBean;
    }
}
