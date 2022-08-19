package com.trent.User;

import com.trent.system.pojo.user.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author: Trent
 * @Date: 2021/11/17 23:13
 * @program: BAM
 * @Description:
 */
public class CurrentUser{
	
	public static User currentUserInfo(){
		return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	}
}
