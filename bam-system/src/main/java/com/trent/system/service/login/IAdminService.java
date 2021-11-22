package com.trent.system.service.login;


import com.trent.common.utils.result.ResultUtil;
import com.trent.system.pojo.admin.Admin;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Trent
 * @since 2021-11-12
 */
public interface IAdminService {
	ResultUtil login(String userName, String password, String captcha, String captchFlag, HttpServletRequest request) throws Exception;
	
	/**
	 * 获取当前用户信息
	 * @param userName
	 * @return
	 */
	Admin getAdminByUserName(String userName);
}
