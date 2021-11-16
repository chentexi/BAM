package com.trent.system.service.impl.menu;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.menu.SysRoleMenuMapper;
import com.trent.system.pojo.menu.SysRoleMenu;
import com.trent.system.service.menu.ISysRoleMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色和菜单关联表 服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements ISysRoleMenuService{

}
