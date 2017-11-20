/**
 * 
 */
package com.imooc.web.aspect;

import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * @author apple Spring AOP 简介 切片(类) 1.切入点(注解) 1-1 在哪些方法上起作用 1-2 在什么时候起作用
 *         2.增强(方法) 起作用时执行的业务逻辑
 */
@Aspect
@Component
public class TimeAspect {

	// @after @before 参照拦截器 @afterthrowing 是在抛出某些异常的时候调用增强方法
	// 在around起作用 在controller->UserController里的方法起作用
	// 别的需求参照https://docs.spring.io/spring/docs/4.3.13.BUILD-SNAPSHOT/spring-framework-reference/htmlsingle/
	// 11.1.2中查看
	@Around("execution(* com.imooc.web.controller.UserController.*(..))")
	public Object handleControllerMethod(ProceedingJoinPoint pjp) throws Throwable {
		// pjp包含拦截住的信息 类名等..
		System.out.println("time aspect start");
		
		Object[] args = pjp.getArgs();
		for (Object object : args) {
			System.out.println("arg is " + object);
		}
		long start = new Date().getTime();

		Object obj = pjp.proceed();// 和filter中 doFilter一样

		//obj中为调用方法中返回的对象  例如调用create  返回User  调用query 返回List<User>
		System.out.println(obj.getClass());
		System.out.println("time aspect 耗时:" + (new Date().getTime() - start));
		System.out.println("time aspect end");
		return obj;

	}
}
