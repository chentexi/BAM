package com.trent.system.mapper.login;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trent.system.pojo.login.Admin;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
public interface AdminMapper extends BaseMapper<Admin>{
	Admin login(String userName, String password);
	
	Admin selectAdminByName(String userName);
}
