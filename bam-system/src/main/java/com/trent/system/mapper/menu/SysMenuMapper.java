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
}
