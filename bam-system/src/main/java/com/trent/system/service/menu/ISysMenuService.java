package com.trent.system.service.menu;


import com.baomidou.mybatisplus.extension.service.IService;
import com.trent.system.pojo.menu.SysMenu;

import java.security.Principal;
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
	
	List<SysMenu> getMenuList(Principal principal);
}
