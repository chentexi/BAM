package com.trent.admin.controller.admin;


import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.service.login.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
@RestController
@RequestMapping("/user")
public class AdminController {
	@Value("${file.uploadFolder}")
	private String path;
	@Value("${apiUrl}")
	private String apiUrl;
	
	@Autowired
	private IAdminService adminService;
	
	@ApiOperation(value = "获取用户数据表")
	@PostMapping("/findAdmin")
	public ResultUtil findAdmin(@RequestBody Admin admin){
		ResultUtil resultUtil = adminService.findAdmin(admin);
		return resultUtil;
	}

	@ApiOperation(value = "添加用户")
	@PostMapping("/addAdmin")
	public ResultUtil addAdmin(@RequestBody Admin admin){
		int result= adminService.addAdmin(admin);
		return ResultUtil.ok();
	}

	@ApiOperation(value = "编辑用户")
	@PostMapping("/updateAdmin")
	public ResultUtil updateAdmin(@RequestBody Admin admin){
		int result= adminService.updateAdmin(admin);
		return ResultUtil.ok();
	}

	@ApiOperation(value = "是否启用该用户")
	@PostMapping("/upadteEnabled")
	public ResultUtil upadteEnabled(@RequestBody Admin admin){
		int result= adminService.upadteEnabled(admin);
		return ResultUtil.ok();
	}
	
	public ResultUtil uploadIcon(){
		return null;
	}
	
}
