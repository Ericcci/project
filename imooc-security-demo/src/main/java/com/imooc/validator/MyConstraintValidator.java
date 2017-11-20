/**
 * 
 */
package com.imooc.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.imooc.service.HelloService;

/**
 * @author apple
 *
 */
public class MyConstraintValidator implements ConstraintValidator<MyConstraint , Object> {

	@Autowired
	public HelloService helloService;
	
	@Override
	public void initialize(MyConstraint arg0) {
		System.out.println("my constraintvalidator init");
	}

	@Override
	public boolean isValid(Object arg0, ConstraintValidatorContext arg1) {
		helloService.greeting("tom");
		System.out.println(arg0);
		return false;
	}

}
