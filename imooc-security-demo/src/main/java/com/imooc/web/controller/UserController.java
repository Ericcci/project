/**
 * 
 */
package com.imooc.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.dto.User;
import com.imooc.dto.UserQueryCondition;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * @author apple
 *
 */
@RestController
@RequestMapping("/user")
public class UserController {

	// @Valid 用于校验对象-->User.class中@NotBlank注解的password对象 (配合hibernate validator)
	// @Valid 和 BindingResult一起使用
	@PostMapping
	public User create(@Valid @RequestBody User user, BindingResult errors) {

		if (errors.hasErrors()) {
			errors.getAllErrors().stream().forEach(error -> System.out.println(error.getDefaultMessage()));
		}

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		// user.setPassword("123");
		// user.setUsername("tom");
		return user;
	}

	// @GetMapping("/user")等价于@RequestMapping(value = "/user", method =
	// RequestMethod.GET)
	@GetMapping
	@JsonView(User.UserSimpleView.class)
	@ApiOperation(value = "用户查询服务") //使用swagger生成自定义注释值
	public List<User> query(UserQueryCondition condition,
			@PageableDefault(size = 15, page = 2, sort = "username,asc") Pageable pageable) {

		System.out.println(ReflectionToStringBuilder.toString(condition, ToStringStyle.MULTI_LINE_STYLE));
		System.out.println(pageable.getPageSize());
		System.out.println(pageable.getPageNumber());
		System.out.println(pageable.getSort());
		List<User> users = new ArrayList<>();
		users.add(new User());
		users.add(new User());
		users.add(new User());
		return users;
	}

	// @RequestMapping(value = "/user/{id:\\d+}", method = RequestMethod.GET)
	@GetMapping("/{id:\\d+}")
	@JsonView(User.UserDetailView.class)
	public User getInfo(@PathVariable String id) {

		// throw new RuntimeException("runtime exception ");
		System.out.println("接入getInfo服务");
		User user = new User();
		user.setUsername("tom");
		user.setPassword("123");
		return user;
	}

	@PutMapping("/{id:\\d+}")
	public User update(@Valid @RequestBody User user, BindingResult errors) {

		if (errors.hasErrors()) {
			// errors.getAllErrors().stream().forEach(error ->
			// System.out.println(error.getDefaultMessage()));
			// 输出
			// may not be empty
			// must be in the past
			errors.getAllErrors().stream().forEach(error -> {
				// FieldError fieldError = (FieldError)error;
				// String message = fieldError.getField() + " " + error.getDefaultMessage();
				System.out.println(error.getDefaultMessage());
			});
		}

		System.out.println(user.getId());
		System.out.println(user.getUsername());
		System.out.println(user.getPassword());
		System.out.println(user.getBirthday());

		user.setId("1");
		// user.setPassword("123");
		// user.setUsername("tom");
		return user;
	}

	@DeleteMapping("/{id:\\d+}")
	public void delete(@ApiParam("用户id") @PathVariable String id) {
		System.out.println(id);
	}
}
