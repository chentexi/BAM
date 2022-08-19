package com.trent.system.service.login;


import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.user.User;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
public interface IUserService{
	ResultUtil login(String userName, String password, String captcha, String captchFlag, HttpServletRequest request) throws Exception;
	
	/**
	 * 获取当前用户信息
	 * @param userName
	 * @return
	 */
	User getUserByUserName(String userName);
	
	/**
	 * 获取用户信息表
	 * @param user
	 * @return
	 */
	ResultUtil findUser(User user);
	
	int addUser(User user);
	
	int updateUser(User user);
	
	int upadteEnabled(User user);
	
	int delUserById(String ids);
}
