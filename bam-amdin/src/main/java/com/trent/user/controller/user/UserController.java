package com.trent.user.controller.user;


import com.trent.common.utils.date.DateUtils;
import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.user.User;
import com.trent.system.service.login.IUserService;
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
public class UserController {
	@Value("${file.uploadFolder}")
	private String path;
	@Value("${apiUrl}")
	private String apiUrl;
	
	@Autowired
	private IUserService UserService;
	
	@ApiOperation(value = " 获取用户数据表 ")
	@PostMapping("/findUser")
	public ResultUtil findUser(@RequestBody User user){
		ResultUtil resultUtil = UserService.findUser(user);
		return resultUtil;
	}

	@ApiOperation(value = "添加用户")
	@PostMapping("/addUser")
	public ResultUtil addUser(@RequestBody User user){
		user.setCreateBy(com.trent.User.CurrentUser.currentUserInfo().getId());
		user.setCreateTime(DateUtils.currentDate());
		int result= UserService.addUser(user);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else {
			return ResultUtil.ok();
		}
	}

	@ApiOperation(value = "编辑用户")
	@PostMapping("/updateUser")
	public ResultUtil updateUser(@RequestBody User user){
		user.setUpdateBy(com.trent.User.CurrentUser.currentUserInfo().getId());
		user.setUpdateTime(DateUtils.currentDate());
		int result= UserService.updateUser(user);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else {
			return ResultUtil.ok();
		}
	}

	@ApiOperation(value = "是否启用该用户")
	@PostMapping("/updateEnabled")
	public ResultUtil upadteEnabled(@RequestBody User user){
		user.setEnabled(user.getEnable2()?true:false);
		int result= UserService.upadteEnabled(user);
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
		int result = UserService.delUserById(ids);
		if( result==ResultUtil.CODE_UPDATE_DEL_ERROR_STATUS ){
			return ResultUtil.fail("操作失败!");
		}else {
			return ResultUtil.ok();
		}
	}
}
