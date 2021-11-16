package com.trent.system.service.impl.menu;


import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.menu.MenuMapper;
import com.trent.system.pojo.menu.Menu;
import com.trent.system.service.menu.IMenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService{

}
