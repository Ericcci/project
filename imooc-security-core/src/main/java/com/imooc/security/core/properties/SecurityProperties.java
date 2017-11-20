/**
 * 
 */
package com.imooc.security.core.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author apple
 *
 */
//注解的意思是这个SecurityProperties会读取imooc.security开头的配置
@ConfigurationProperties(prefix = "imooc.security")
public class SecurityProperties {

	private BrowserProperties browser = new BrowserProperties();

	public BrowserProperties getBrowser() {
		return browser;
	}

	public void setBrowser(BrowserProperties browser) {
		this.browser = browser;
	}
	
	
}
