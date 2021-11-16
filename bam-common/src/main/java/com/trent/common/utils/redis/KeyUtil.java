package com.trent.common.utils.redis;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Trent
 * @Date: 2021/11/16 15:14
 * @program: BAM
 * @Description:
 */
public class KeyUtil{
	
	public static final String SEPARATOR = ":";
	public static final int MIN_LENGTH = 7;
	
	/**
	 * 格式化Redis的key
	 *
	 * @param cm
	 * @param cs
	 * @param key
	 * @return
	 */
	public static String formatKey(CacheModule cm, CacheScope cs, String key) {
		if (isValid(key)){
			return key;
		}
		return cm.value() + SEPARATOR + key + SEPARATOR + cs.value();
	}
	
	/**
	 * 从已格式的redis的key中获取业务自定义的key
	 *
	 * @param formatKey
	 * @return
	 */
	public static String getKey(String formatKey){
		checkKey(formatKey);
		return formatKey.substring(3,formatKey.length() - 3);
	}
	
	/**
	 * 检查格式化key是否有效
	 *
	 * @param formatKeyList
	 */
	public static void checkKeys(List<String> formatKeyList){
		for (String formatKey : formatKeyList) {
			if (!KeyUtil.isValid(formatKey)) {
				throw new RuntimeException("无效的格式 - " + formatKey +
						                           " , eg: CacheModule + SEPARATOR + key + SEPARATOR + CacheScope");
			}
		}
	}
	public static void checkKeys(String... formatKeys){
		checkKeys(Arrays.asList(formatKeys));
	}
	
	public static void checkKey(String formatKey){
		if (!KeyUtil.isValid(formatKey)) {
			throw new RuntimeException("无效的格式 - " + formatKey +
					                           " , eg: CacheModule + SEPARATOR + key + SEPARATOR + CacheScope");
		}
	}
	
	public static boolean isAllValid(String... formatKeys) {
		return isAllValid(Arrays.asList(formatKeys));
	}
	
	public static boolean isAllValid(List<String> formatKeyList) {
		for (String formatKey : formatKeyList) {
			if (!isValid(formatKey)) {
				return false;
			}
		}
		return true;
	}
	
	public static boolean isValid(String formatKey) {
		return validLength(formatKey) && validPre(formatKey) && validPost(formatKey);
	}
	
	public static boolean validLength(String formatKey) {
		return (formatKey != null && formatKey.trim().length() >= MIN_LENGTH);
	}
	
	public static boolean validPre(String formatKey) {
		String pre = formatKey.substring(0, 2);
		String separator = formatKey.substring(2, 3);
		return CacheModule.typeOf(pre) != null && separator.equals(SEPARATOR);
	}
	
	public static boolean validPost(String formatKey) {
		int length = formatKey.length();
		String post = formatKey.substring(length - 2, length);
		String separator = formatKey.substring(length - 3, length - 2);
		return separator.equals(SEPARATOR) && CacheScope.typeOf(post) != null;
	}
}
