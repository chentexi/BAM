package com.trent.system.mapper.menu;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trent.system.pojo.menu.SysMenu;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <p>
 * 菜单权限表 Mapper 接口
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
public interface SysMenuMapper extends BaseMapper<SysMenu>{
	
	List<SysMenu> getMenuListAll();
}
