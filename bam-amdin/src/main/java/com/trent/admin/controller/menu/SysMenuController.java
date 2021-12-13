package com.trent.admin.controller.menu;


import com.alibaba.fastjson.JSON;
import com.trent.common.CurrentUser;
import com.trent.common.utils.redis.CacheModule;
import com.trent.common.utils.redis.CacheScope;
import com.trent.common.utils.redis.KeyUtil;
import com.trent.common.utils.redis.RedisUtil;
import com.trent.common.utils.result.ResultMapUtil;
import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.admin.Admin;
import com.trent.system.pojo.menu.SysMenu;
import com.trent.system.service.login.IAdminService;
import com.trent.system.service.menu.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Condition;

/**
 * <p>
 * 菜单权限表 前端控制器
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@RestController
@Api(tags = "SysMenuController")
@RequestMapping("/menu")
public class SysMenuController {
	@Autowired
	private ISysMenuService menuService;
	@Autowired
	private IAdminService adminService;
	@Autowired
	private RedisUtil redisUtil;
	
	@ApiOperation(value = "菜单列表")
	@GetMapping("/menuList")
	public Map<String,Object> getMenuList(Principal principal){
		if( principal==null ){
			return ResultMapUtil.fail("请先登录!");
		}
		Admin admin = (Admin) CurrentUser.currentAdminInfo();
		
		String key = KeyUtil.formatKey(CacheModule.VPFORM, CacheScope.CACHE, "menu_" + admin.getId());
		List<SysMenu> menuList =JSON.parseArray(redisUtil.get(key),SysMenu.class);
		if( !CollectionUtils.isEmpty(menuList) ){
			return ResultMapUtil.success("成功",menuList);
		}else{
			menuList= menuService.getMenuList(admin);
			//redisUtil.set(key,JSON.toJSONString(menuList));
		}
		return ResultMapUtil.success("成功",menuList);
	}
	@ApiOperation(value = "保存/修改")
	@PostMapping("/save")
	public ResultUtil saveMenu(){
		return null;
	}
	
	@ApiOperation(value = "菜单删除")
	@GetMapping("/delect")
	public ResultUtil delectMenu(Integer id, Principal principal){
		
		int result = menuService.delectMenuById(id);
		if( ResultUtil.CODE_UPDATE_DEL_STATUS==result ){
			return ResultUtil.ok() ;
		}
		return ResultUtil.fail("删除失败!请联系管理员!");
	}
	
}
