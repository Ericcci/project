package com.imooc.web.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.imooc.web.filter.TimeFilter;
import com.imooc.web.interceptor.TimeInterceptor;
/*
 * 使用第三方过滤器
 * */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	public TimeInterceptor timeInceptor;
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(timeInceptor);
	}
	
	@Bean
	public FilterRegistrationBean timeFilter() {
		
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		
		TimeFilter timeFilter = new TimeFilter();
		registrationBean.setFilter(timeFilter);
		
		//拦截指定路径
		List<String> urls = new ArrayList<>();
		urls.add("/*");
		registrationBean.setUrlPatterns(urls);
		return registrationBean;
	}
}
