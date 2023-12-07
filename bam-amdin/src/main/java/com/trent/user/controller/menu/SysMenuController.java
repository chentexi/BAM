package com.trent.user.controller.menu;


import com.alibaba.fastjson.JSON;
import com.trent.User.CurrentUser;
import com.trent.common.utils.date.DateUtils;
import com.trent.common.utils.redis.CacheModule;
import com.trent.common.utils.redis.CacheScope;
import com.trent.common.utils.redis.KeyUtil;
import com.trent.common.utils.redis.RedisUtil;
import com.trent.common.utils.result.ResultCode;
import com.trent.common.utils.result.ResultMapUtil;
import com.trent.common.utils.result.ResultVo;
import com.trent.system.pojo.user.User;
import com.trent.system.pojo.menu.SysMenu;
import com.trent.system.service.login.IUserService;
import com.trent.system.service.menu.ISysMenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
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
@Api(tags = "SysMenuController")
@RequestMapping("/menu")
public class SysMenuController {
	@Resource
	private ISysMenuService menuService;
	@Resource
	private IUserService UserService;
	@Resource
	private RedisUtil redisUtil;

	@ApiOperation(value = "菜单列表")
	@GetMapping("/menuList")
	public Map<String, Object> getMenuList(Principal principal) {
		if (principal == null) {
			return ResultMapUtil.fail("请先登录!");
		}
		User user = CurrentUser.currentUserInfo();

		String key = KeyUtil.formatKey(CacheModule.VPFORM, CacheScope.CACHE, "menu_" + user.getId());
		List<SysMenu> menuList = JSON.parseArray(redisUtil.get(key), SysMenu.class);
		if (!CollectionUtils.isEmpty(menuList)) {
			return ResultMapUtil.success("成功", menuList);
		} else {
			menuList = menuService.getMenuList(user);
			//redisUtil.set(key,JSON.toJSONString(menuList));
		}
		return ResultMapUtil.success("成功", menuList);
	}

	@ApiOperation(value = "所有菜单列表")
	@PostMapping("/menuLists")
	public Map<String, Object> getMenuLists(@RequestBody SysMenu sysMenuParams) {
		User principal = CurrentUser.currentUserInfo();
		if (principal == null) {
			return ResultMapUtil.fail("请先登录!");
		}

		String key = KeyUtil.formatKey(CacheModule.VPFORM, CacheScope.CACHE, "menu_" + principal.getId());
		List<SysMenu> menuList = JSON.parseArray(redisUtil.get(key), SysMenu.class);
		if (!CollectionUtils.isEmpty(menuList)) {
			return ResultMapUtil.success("成功", menuList);
		} else {
			menuList = menuService.getMenuLists(sysMenuParams, principal);
			//redisUtil.set(key,JSON.toJSONString(menuList));
		}
		SysMenu sysMenu = new SysMenu();
		sysMenu.setMenuId(0);
		sysMenu.setMenuName("顶级类目");
		sysMenu.setChildren(menuList);

		List<SysMenu> sysMenus = new ArrayList<>();
		sysMenus.add(sysMenu);

		Map<String, Object> objectMap = new HashMap<>(2);
		objectMap.put("data", menuList);
		objectMap.put("mainMenu", sysMenus);
		return ResultMapUtil.success("成功", objectMap);
	}

	@ApiOperation(value = "保存/修改")
	@PostMapping("/save")
	public ResultVo saveMenu() {
		return null;
	}

	@ApiOperation(value = "菜单删除")
	@GetMapping("/delect")
	public ResultVo delectMenu(Integer id, Principal principal) {
		if (menuService.delectMenuById(id)) {
			return new ResultVo(ResultCode.SUCCESS);
		}
		return new ResultVo(ResultCode.FAILED);
	}

	@ApiOperation(value = "更改菜单显示")
	@PostMapping("/updateVisible")
	public ResultVo updateVisible(@RequestParam("menuId") String menuId, @RequestParam("visible") String visible) {
		if (menuService.updateMenuVisible(menuId, visible)) {
			return new ResultVo(ResultCode.SUCCESS);
		}
		return new ResultVo(ResultCode.FAILED);
	}

	@ApiOperation(value = "更改菜单是否启用")
	@PostMapping("/updateEnable")
	public ResultVo updateEnable(@RequestParam("menuId") String menuId, @RequestParam String enable) {
		if (menuService.updateEnable(menuId, enable)) {
			return new ResultVo(ResultCode.SUCCESS);
		}
		return new ResultVo(ResultCode.FAILED, "修改失败!请联系管理员!");
	}
	
	@ApiOperation(value = "添加菜单")
	@PostMapping("/addMenu")
	public ResultVo addMenu(@RequestBody SysMenu sysMenu) {
		sysMenu.setCreateBy(CurrentUser.currentUserInfo().getId());
		sysMenu.setCreateTime(DateUtils.currentDate());
		if (menuService.addMenu(sysMenu)) {
			return new ResultVo(ResultCode.SUCCESS);
		}
		return new ResultVo(ResultCode.FAILED);
	}


	@ApiOperation(value = "编辑菜单")
	@PostMapping("/updateMenu")
	public ResultVo updateMenu(@RequestBody SysMenu sysMenu) {
		sysMenu.setUpdateBy(CurrentUser.currentUserInfo().getId());
		sysMenu.setUpdateTime(DateUtils.currentDate());
		if (menuService.updateMenu(sysMenu)) {
			return new ResultVo(ResultCode.SUCCESS);
		}
		return new ResultVo(ResultCode.FAILED);
	}

}
