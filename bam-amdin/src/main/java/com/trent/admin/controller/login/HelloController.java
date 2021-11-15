package com.trent.admin.controller.login;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Trent
 * @Date: 2021/11/15 0:55
 * @program: BAM
 * @Description:
 */
@Api(tags = "HelloController")
@RestController
public class HelloController{
	@GetMapping("/hello")
	public String hello(){
		return "hello";
	}
}
