package com.trent.admin.controller.admin;


import com.trent.admin.CurrentUser;
import com.trent.common.utils.date.DateUtils;
import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.service.login.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
		admin.setCreateBy(CurrentUser.currentAdminInfo().getId());
		admin.setCreateTime(DateUtils.currentDate());
		int result= adminService.addAdmin(admin);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else {
			return ResultUtil.ok();
		}
	}

	@ApiOperation(value = "编辑用户")
	@PostMapping("/updateAdmin")
	public ResultUtil updateAdmin(@RequestBody Admin admin){
		admin.setUpdateBy(CurrentUser.currentAdminInfo().getId());
		admin.setUpdateTime(DateUtils.currentDate());
		int result= adminService.updateAdmin(admin);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else {
			return ResultUtil.ok();
		}
	}

	@ApiOperation(value = "是否启用该用户")
	@PostMapping("/updateEnabled")
	public ResultUtil upadteEnabled(@RequestBody Admin admin){
		admin.setEnabled(admin.getEnable2()?true:false);
		int result= adminService.upadteEnabled(admin);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else{
			return ResultUtil.ok();
		}
	}
	
	public ResultUtil uploadIcon(){
		return null;
	}
	
	@ApiOperation(value = "删除用户")
	@PostMapping("/delUserById")
	public ResultUtil delUserById(@RequestParam("ids") String ids){
		int result = adminService.delUserById(ids);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else {
			return ResultUtil.ok();
		}
	}
}
