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
	
	int delectMenuById(Integer id);
	
	int updateMenuVisible(String menuId, String visible);
	
	int updateMenuVisible(SysMenu sysMenu);
	
	List<SysMenu> getMenuLists(SysMenu sysMenuParams, User user);
	
	int updateEnable(String menuId, String enable);
	
	int addMenu(SysMenu sysMenu);
	
	int updateMenu(SysMenu sysMenu);
}
