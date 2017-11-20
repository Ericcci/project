/**
 * 
 */
package com.imooc.web.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;


/**
 * @author apple
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	//Before 会在测试用例之前执行
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}
	
	@Test
	public void whenQuerySucess() throws Exception {
		/*
		 * perform 执行
		 * MockMvcRequestBuilders.get  模拟发出一个get请求
		 * andExpect 期望返回的结果
		 * MockMvcResultMatchers.status().isOk() 希望返回的状态码为200
		 * */
		 String result = mockMvc.perform(get("/user")
				.param("username", "jane")
				.param("age", "11")
				.param("ageTo", "50")
				.param("xxx", "test")
//				.param("size", "20")
//				.param("page", "3")
//				.param("sort", "age,DESC")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.length()").value(3))
		        .andReturn().getResponse().getContentAsString();
		 
		 System.out.println(result);
	}
	
	@Test
	public void whenGetInfoSuccess() throws Exception {
		String result = mockMvc.perform(get("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.username").value("tom"))
		        .andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenGetInfoFail() throws Exception {
		mockMvc.perform(get("/user/a")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
		        .andExpect(status().is4xxClientError());
	}
	
	@Test
	public void whenCreateSuccess() throws Exception {
		
		Date date = new Date();
		System.out.println(date.getTime());
		String content = "{\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(post("/user")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value("1"))
		        .andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenUpdateSuccess() throws Exception {
		
		Date date = new Date(LocalDateTime.now().plusYears(1).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli());
		System.out.println(date.getTime());
		String content = "{\"id\":\"1\",\"username\":\"tom\",\"password\":null,\"birthday\":"+date.getTime()+"}";
		String result = mockMvc.perform(put("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.content(content))
		        .andExpect(status().isOk())
		        .andExpect(jsonPath("$.id").value("1"))
		        .andReturn().getResponse().getContentAsString();
		
		System.out.println(result);
	}
	
	@Test
	public void whenDeleteSuccess() throws Exception {
		mockMvc.perform(delete("/user/1")
				.contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(status().isOk());
	}
}
