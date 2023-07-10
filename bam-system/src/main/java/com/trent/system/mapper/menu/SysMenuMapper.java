package com.trent.system.mapper.menu;


import com.trent.system.pojo.menu.SysMenu;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@Repository
public interface SysMenuMapper {
	
	List<SysMenu> getMenuListAll();
	
	Boolean delectMenuById(Integer id);
	
	List<SysMenu> getMenuListById(Integer id);
	
	Boolean updateMenuVisible(String menuId, String visible);
	
	Boolean updateMenuVisible(SysMenu sysMenu);
	
	List<SysMenu> getMenuLists(SysMenu sysMenuParams);
	
	Boolean updateEnable(String menuId, String enable);
	
	Boolean addMenu(SysMenu sysMenu);
	
	Boolean updateMenu(SysMenu sysMenu);
	
}
