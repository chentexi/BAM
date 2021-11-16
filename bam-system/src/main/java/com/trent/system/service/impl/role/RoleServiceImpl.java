package com.trent.system.service.impl.role;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trent.system.mapper.role.RoleMapper;
import com.trent.system.pojo.role.Role;
import com.trent.system.service.role.IRoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色信息表 服务实现类
 * </p>
 *
 * @author Trent
 * @since 2021-11-16
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService{

}
