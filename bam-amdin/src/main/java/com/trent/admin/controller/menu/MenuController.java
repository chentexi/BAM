package com.trent.admin.controller.menu;


import com.trent.common.utils.result.ResultMapUtil;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.pojo.menu.SysMenu;
import com.trent.system.service.menu.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Permission;
import java.security.Principal;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@RestController
@Api(tags = "MenuController")
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private ISysMenuService menuService;
	
	@ApiOperation(value = "菜单列表")
	@GetMapping("/menuList")
	public Map<String,Object> getMenuList(Principal principal){
		if( principal==null ){
			return ResultMapUtil.fail("请先登录!");
		}
		List<SysMenu> menuList= menuService.getMenuList(principal);
		return ResultMapUtil.success("成功",menuList);
	}

}
