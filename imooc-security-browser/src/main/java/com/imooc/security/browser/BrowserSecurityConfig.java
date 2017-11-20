/**
 * 
 */
package com.imooc.security.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.imooc.security.core.properties.SecurityProperties;

/**
 * @author apple
 *
 */
@Configuration
public class BrowserSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private SecurityProperties securityProperties;

	@Autowired
	private AuthenticationSuccessHandler imoocAuthenticationSuccessHandler;
	
	@Autowired
	private AuthenticationFailureHandler imoocAuthenticationFailureHandler;
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		// BCryptPasswordEncoder 为 PasswordEncoder 的实现类
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http.formLogin() // 表单登录
				.loginPage("/authentication/require")
				// 以下的请求会经过UsernamePasswordAuthenticationFilter过滤
				.loginProcessingUrl("/authentication/form")
				// http.httpBasic() //httpbasic登录
				//自定义成功处理
				.successHandler(imoocAuthenticationSuccessHandler)
				//自定义失败处理
				.failureHandler(imoocAuthenticationFailureHandler)
				.and()
				// authorizeRequests 授权以下配置
				.authorizeRequests()
				.antMatchers("/authentication/require", securityProperties.getBrowser().getLoginPage()).permitAll()
				// 任何请求
				.anyRequest()
				// 都需要身份认证
				.authenticated().and().csrf().disable();
	}
}
