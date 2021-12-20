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
	
	int delectMenuById(Integer id);
	
	List<SysMenu> getMenuListById(Integer id);
	
	int updateMenuVisible(String menuId, String visible);
	
	int updateMenuVisible(SysMenu sysMenu);
	
	List<SysMenu> getMenuLists(SysMenu sysMenuParams);
	
	int updateEnable(String menuId, String enable);
	
	int addMenu(SysMenu sysMenu);
	
	int updateMenu(SysMenu sysMenu);
	
}
