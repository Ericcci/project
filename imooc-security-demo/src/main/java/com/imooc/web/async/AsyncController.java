/**
 * 
 */
package com.imooc.web.async;

import java.util.concurrent.Callable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author apple
 *
 */
@RestController
public class AsyncController {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	//使用Runnable异步处理Rest服务
	@RequestMapping("/order")
	public Callable<String> order() throws Exception {
		logger.info("主线程开始");
		Callable<String> result = new Callable<String>() {
			
			@Override
			public String call() throws Exception {
				logger.info("副线程开始");
				Thread.sleep(1000);
				logger.info("副线程返回");
				return "success";
			}
		};
		logger.info("主线程返回");
		return result;
	}
}
