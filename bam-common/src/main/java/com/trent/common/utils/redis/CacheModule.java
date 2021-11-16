package com.trent.common.utils.redis;

/**
 * @Author: Trent
 * @Date: 2021/11/16 15:15
 * @program: BAM
 * @Description: Redis缓存Key必须包含所属模块，并将其作为前缀,以冒号(:)分割
 */
public enum CacheModule{
	/**
	 * 电商
	 */
	STORE("eb","eb"),
	/**
	 * 验证码缓存
	 */
	CAPTCHA("cc","cc"),
	/**
	 * mvvg - 平台级用缓存
	 */
	VPFORM("vm", "vm");
	
	private String md;
	private String name;
	
	private CacheModule(String md, String name){
		this.md = md;
		this.name = name;
	}
	
	public String value(){
		return md;
	}
	
	public static CacheModule typeOf(String type){
		CacheModule result = null;
		for (CacheModule typeEnum : CacheModule.values()) {
			if (typeEnum.value().equals(type)) {
				result = typeEnum;
				break;
			}
		}
		return result;
	}
}
