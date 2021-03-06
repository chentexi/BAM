package com.trent.common.utils.uuid;

import java.util.UUID;

/**
 * @Author: Trent
 * @Date: 2021/11/12 18:28
 * @program: BAM
 * @Description:
 */
public class UUIDUtil{
	/**
	 * 获得4个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get4UUID(){
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[1];
	}
	
	/**
	 * 获得8个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get8UUID(){
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0];
	}
	
	/**
	 * 获得12个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get12UUID(){
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1];
	}
	
	/**
	 * 获得16个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get16UUID(){
		
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[2];
	}
	
	/**
	 * 获得20个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get20UUID(){
		
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[2] + idd[3];
	}
	
	/**
	 * 获得24个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get24UUID(){
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[4];
	}
	
	/**
	 * 获得32个长度的十六进制的UUID
	 *
	 * @return UUID
	 */
	public static String get32UUID(){
		UUID id = UUID.randomUUID();
		String[] idd = id.toString().split("-");
		return idd[0] + idd[1] + idd[2] + idd[3] + idd[4];
	}
	
	public static void main(String[] args){
		String uuid4 = get4UUID();
		System.out.println("uuid4:"+uuid4);
		
		String uuid8 = get8UUID();
		System.out.println("uuid8"+uuid8);
		
		String uuid = get32UUID();
		System.out.println(uuid);
	}
}
