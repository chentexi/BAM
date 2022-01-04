package com.trent.system.mapper.login;

import com.trent.system.pojo.admin.Admin;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
@Repository
public interface AdminMapper {
	Admin login(String userName, String password);
	
	Admin selectAdminByName(String userName);
	
	List<Admin> findAdmin(Admin admin);
	
	int upadteEnabled(Admin admin);
}
