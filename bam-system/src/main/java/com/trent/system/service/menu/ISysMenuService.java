package com.trent.system.service.menu;


import com.trent.system.pojo.admin.Admin;
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
	
	List<SysMenu> getMenuList(Admin admin);
	
	int delectMenuById(Integer id);
}
