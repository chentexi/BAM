package com.trent.system.service.menu;


import com.trent.system.pojo.user.User;
import com.trent.system.pojo.menu.SysMenu;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
public interface ISysMenuService {
	
	List<SysMenu> getMenuList(User user);
	
	Boolean delectMenuById(Integer id);
	
	Boolean updateMenuVisible(String menuId, String visible);
	
	Boolean updateMenuVisible(SysMenu sysMenu);
	
	List<SysMenu> getMenuLists(SysMenu sysMenuParams, User user);
	
	Boolean updateEnable(String menuId, String enable);
	
	Boolean addMenu(SysMenu sysMenu);
	
	Boolean updateMenu(SysMenu sysMenu);
}
