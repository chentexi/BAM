package com.trent.common.utils.result;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Trent
 * @Date: 2021/11/12 13:27
 * @program: BAM
 * @Description:
 */
@Getter
@Setter
public class ResultMapUtil implements Serializable{
	private static final long serialVersionUID = 1852535877532127689L;
	
	public static final String APPLICATION_NUM_YES = "yes";
	public static final String APPLICATION_NUM_NO = "no";
	public static final String APPLICATION_NUM_OK = "ok";
	
	public static final String APPLICATION_SUCCESS = "success";
	public static final String APPLICATION_ERROR = "error";
	
	
	public static Map<String, Object> success(String msg) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("code", "1");
		result.put("status", "success");
		result.put("msg", msg);
		return result;
	}
	
	public static Map<String, Object> success(String msg, Object data) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", "1");
		result.put("status", "success");
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	public static Map<String, Object> success(String code, String msg, Object data) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", code);
		result.put("status", "success");
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	/**
	 * 自定义的 适用于 page
	 * @param code
	 * @param msg
	 * @param data
	 * @return
	 */
	public static Map<String, Object> success(String code, String msg, Object data,String pageKey,Object page) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", code);
		result.put("status", "success");
		result.put("msg", msg);
		result.put("data", data);
		result.put(pageKey, page);
		return result;
	}
	public static Map<String, Object> success(String code, String status, String msg, Object data) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", code);
		result.put("status", status);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	public static Map<String, Object> fail(String msg) {
		Map<String, Object> result = new HashMap<>(3);
		result.put("code", "0");
		result.put("status", "fail");
		result.put("msg", msg);
		return result;
	}
	
	public static Map<String, Object> fail(String msg, Object data) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", "0");
		result.put("status", "fail");
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	public static Map<String, Object> fail(String code, String msg, Object data) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", code);
		result.put("status", "fail");
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
	
	public static Map<String, Object> fail(String code, String status, String msg, Object data) {
		Map<String, Object> result = new HashMap<>(4);
		result.put("code", code);
		result.put("status", status);
		result.put("msg", msg);
		result.put("data", data);
		return result;
	}
}
