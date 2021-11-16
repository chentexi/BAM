package com.trent.system.service.impl.menu;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.menu.RoleMapper;
import com.trent.system.pojo.login.Role;
import com.trent.system.service.menu.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
