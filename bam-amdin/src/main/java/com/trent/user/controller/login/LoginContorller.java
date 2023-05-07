package com.trent.user.controller.login;

import com.trent.common.utils.result.ResultVo;
import com.trent.system.pojo.user.User;
import com.trent.system.pojo.user.UserLoginParam;
import com.trent.system.service.login.IUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * @Author: Trent
 * @Date: 2021/11/12 13:34
 * @program: BAM
 * @Description:
 */
@Api(tags = "LoginContorller")
@RestController
public class LoginContorller{
	
	@Autowired
	private IUserService userService;
	
	@ApiOperation(value = "登录之后返回token")
	@PostMapping("/login")
	public ResultVo login(@RequestBody UserLoginParam userLoginParam, HttpServletRequest request) throws Exception{
		return userService.login(userLoginParam.getUserName(), userLoginParam.getPassWord(), userLoginParam.getCaptcha(), userLoginParam.getCaptchFlag(), request);
	}
	
	
	@ApiOperation(value = "获取当前用户信息")
	@GetMapping("/admin/info")
	public User getAdminInfo(Principal principal) {
		if (null == principal) {
			return null;
		}
		String username = principal.getName();
		User user = userService.getUserByUserName(username);
		user.setPassWord(null);
		return user;
	}
	@ApiOperation(value = "退出登录")
	@PostMapping("/logout")
	public ResultVo logout() {
		//前端收到状态码,删除客户端的token
		return new ResultVo("退出登陆成功");
	}
	
}
