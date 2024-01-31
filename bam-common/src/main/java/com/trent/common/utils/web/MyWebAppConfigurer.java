package com.trent.common.utils.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: Trent
 * @Date: 2021/12/27 23:15
 * @program: BAM
 * @Description:添加资源注释器
 */
public class MyWebAppConfigurer implements WebMvcConfigurer{
	@Value("${file.staticAccessPath}")
	private String staticAccessPath;
	@Value("${file.uploadFolder}")
	private String uploadFolder;
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry){
		registry.addResourceHandler(staticAccessPath).addResourceLocations("file:"+uploadFolder);
		
	}
}