package com.trent.system.service.impl;

import com.trent.system.mapper.SysLoginMapper;
import com.trent.system.service.ISysLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

/**
 * @Author: Trent
 * @Date: 2021/6/24 9:54
 * @program: MultiModuleProject
 * @Description:
 */
@Service
@RequiredArgsConstructor
public class SysLoginServiceImpl implements ISysLoginService{
	
	private SysLoginMapper sysLoginMapper;
	
	@Override
	public void login(){
		sysLoginMapper.login();
	}
}
