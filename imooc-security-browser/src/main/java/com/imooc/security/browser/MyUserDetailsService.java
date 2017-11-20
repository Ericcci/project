/**
 * 
 */
package com.imooc.security.browser;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author apple
 *
 */
@Component
public class MyUserDetailsService implements UserDetailsService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		logger.info("登录用户名" + username);
		//根据用户名查找用户信息
		//从数据库中查找用户信息查看是否被冻结/锁定等状态
		//目前为写死状态
		
		//加密后的密码  这句话应该写在注册的时候
		String password = passwordEncoder.encode("123456");
		//passwordEncoder.matches("用户前台输入的密码","数据库取出的密码")
		logger.info("在数据库的密码为:" + password);
		//以下的password应该为拿出在数据库存的值
		return new User(username, password ,true,true,true,true, AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));
	}

}
