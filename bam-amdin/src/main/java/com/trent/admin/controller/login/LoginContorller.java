package com.trent.admin.controller.login;

import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.pojo.admin.AdminLoginParam;
import com.trent.system.service.login.IAdminService;
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
	private IAdminService adminService;
	
	@ApiOperation(value = "登录之后返回token")
	@PostMapping("/login")
	public ResultUtil login(@RequestBody AdminLoginParam adminLoginParam, HttpServletRequest request) throws Exception{
		return adminService.login(adminLoginParam.getUserName(), adminLoginParam.getPassWord(),adminLoginParam.getCaptcha(),adminLoginParam.getCaptchFlag(), request);
	}
	
	
	@ApiOperation(value = "获取当前用户信息")
	@GetMapping("/admin/info")
	public Admin getAdminInfo(Principal principal) {
		if (null == principal) {
			return null;
		}
		String username = principal.getName();
		Admin admin = adminService.getAdminByUserName(username);
		admin.setPassWord(null);
		return admin;
	}
	@ApiOperation(value = "退出登录")
	@PostMapping("/logout")
	public ResultUtil logout() {
		//前端收到状态码,删除客户端的token
		return ResultUtil.ok("注销成功！");
	}
	
}
