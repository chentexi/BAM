package com.trent.system.service.impl.menu;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.menu.RoleMenuMapper;
import com.trent.system.pojo.menu.RoleMenu;
import com.trent.system.service.menu.IRoleMenuService;
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
public class RoleMenuServiceImpl extends ServiceImpl<RoleMenuMapper, RoleMenu> implements IRoleMenuService{

}
