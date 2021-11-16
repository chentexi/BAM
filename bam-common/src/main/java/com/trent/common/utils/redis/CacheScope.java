package com.trent.common.utils.redis;

/**
 * @Author: Trent
 * @Date: 2021/11/16 15:14
 * @program: BAM
 * @Description:  Redis缓存Key必须包含用途的简称，并作为其后缀,以冒号(:)分割
 */
public enum CacheScope{
	/**
	 * 利用Redis的数据结构来做索引，提高查询速度
	 */
	INDEX("ix","ix"),
	/**
	 * 将Redis作为公共缓存
	 */
	CACHE("ca","ca"),
	/**
	 * 利用Redis实现排序功能
	 */
	SORT("st","st"),
	/**
	 * 将Redis作为可持久化的数据库
	 */
	PERSISTENT("db","db"),
	/**
	 * 使用Redis的原子操作，实现锁功能
	 */
	LOCK("lk","lk"),
	/**
	 * 使用Redis缓存的失效性，实现计时功能
	 */
	TIME("tm","tm"),
	/**
	 * 使用Redis的原子性及高速访问，实现计数器功能
	 */
	COUNT("ct","ct"),
	/**
	 * 使用Redis的数据结构，实现基本的队列
	 */
	QUEUE("qe","qe"),
	/**
	 * 使用Redis的公共缓存，保存业务标记
	 */
	TAG("tg","tg");
	
	private String scope;
	private String name;
	
	private CacheScope(String scope, String name){
		this.scope = scope;
		this.name = name;
	}
	
	public String value(){
		return scope;
	}
	
	public static CacheScope typeOf(String type){
		CacheScope result = null;
		for (CacheScope typeEnum : CacheScope.values()) {
			if (typeEnum.value().equals(type)) {
				result = typeEnum;
				break;
			}
		}
		return result;
	}
}
