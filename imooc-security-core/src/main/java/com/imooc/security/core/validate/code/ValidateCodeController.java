/**
 * 
 */
package com.imooc.security.core.validate.code;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 38065
 *
 */
@RestController
public class ValidateCodeController {

	@GetMapping("/code/image")
	public void createCode(HttpServletRequest request,HttpServletResponse response) {
		
	}
}
