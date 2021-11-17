package com.trent.common;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: Trent
 * @Date: 2021/11/17 23:13
 * @program: BAM
 * @Description:
 */
public class CurrentUser{
	
	public static Object currentAdminInfo(){
		return SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
