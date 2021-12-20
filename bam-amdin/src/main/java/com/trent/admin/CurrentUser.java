package com.trent.admin;

import com.trent.system.pojo.admin.Admin;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: Trent
 * @Date: 2021/11/17 23:13
 * @program: BAM
 * @Description:
 */
public class CurrentUser{
	
	public static Admin currentAdminInfo(){
		return (Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
