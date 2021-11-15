package com.trent.common.utils.result;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @Author: Trent
 * @Date: 2021/11/12 14:04
 * @program: BAM
 * @Description:
 */
@Getter
@Setter
public class ResultUtil{
	public static final Integer CODE_SUCCESS = 200;
	
	private static final long serialVersionUID = -2436709332711007968L;
	/** 定义jackson对象 */
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/** 响应业务状态 */
	private Integer status;
	/** 响应消息 */
	private String msg;
	/** 响应中的数据 */
	private Object data;
	/** 响应业务状态 */
	private Integer code;
	/** 响应业务状态 */
	private String info;
	/** 是否响应 */
	private Boolean success;

	public ResultUtil(Integer status, String msg, Object data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	
	public ResultUtil(String info, Object data){
		this.code = 200;
		this.info = info;
		this.data = data;
	}
	
	public ResultUtil(Integer code, String info){
		this.code = code;
		this.info = info;
	}
	
	public ResultUtil(Boolean success, String info, Object data){
		this.success = success;
		this.info = info;
		this.data = data;
	}
	
	public ResultUtil(Boolean success, String info){
		this.success = success;
		this.info = info;
	}
	public static ResultUtil build(Integer status, String msg, Object data){
		return new ResultUtil(status, msg, data);
	}
	
	public static ResultUtil ok(String info, Object data){
		return new ResultUtil(info, data);
	}
	public static ResultUtil ok(Boolean success,String info,Object data){
		return new ResultUtil(success,info,data);
	}
	public static ResultUtil ok(Boolean success,String info){
		return new ResultUtil(success,info);
	}
	public static ResultUtil ok(Integer code,String info,Object data){
		return new ResultUtil(code,info,data);
	}
	public static <T> ResultUtil ok(T t){
		return new ResultUtil("操作成功", t);
	}
	
	public static ResultUtil ok(){
		return new ResultUtil("操作成功", null);
	}
	
	public static ResultUtil success(Object data){
		return new ResultUtil(true, "操作成功", data);
	}
	
	public static ResultUtil success(String msg){
		return new ResultUtil(true, msg, null);
	}
	public static ResultUtil success(){
		return new ResultUtil(true, "操作成功", null);
	}
	
	public static ResultUtil error(String msg, Object data){
		return new ResultUtil(0, msg, data);
	}
	
	public static ResultUtil error(String msg){
		return new ResultUtil(0, msg, null);
	}
	
	public static ResultUtil error(Integer code, String info){
		return new ResultUtil(code, info);
	}
	public static ResultUtil error(Boolean success, String info){
		return new ResultUtil(success, info);
	}
	
	
	public ResultUtil(){
	
	}
	
	public static ResultUtil build(Integer status, String msg){
		return new ResultUtil(status, msg, null);
	}
	

	public Integer getStatus(){
		return status;
	}
	
	/**
	 * 将json结果集转化为ResultUtil对象
	 *
	 * @param jsonData json数据
	 * @param clazz    ResultUtil中的object类型
	 * @return
	 */
	public static ResultUtil formatToPojo(String jsonData, Class<?> clazz){
		try{
			if( clazz == null ){
				return MAPPER.readValue(jsonData, ResultUtil.class);
			}
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if( clazz != null ){
				if( data.isObject() ){
					obj = MAPPER.readValue(data.traverse(), clazz);
				}else if( data.isTextual() ){
					obj = MAPPER.readValue(data.asText(), clazz);
				}
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		}catch( Exception e ){
			return null;
		}
	}
	
	/**
	 * 没有object对象的转化
	 *
	 * @param json
	 * @return
	 */
	public static ResultUtil format(String json){
		try{
			return MAPPER.readValue(json, ResultUtil.class);
		}catch( Exception e ){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Object是集合转化
	 *
	 * @param jsonData json数据
	 * @param clazz    集合中的类型
	 * @return
	 */
	public static ResultUtil formatToList(String jsonData, Class<?> clazz){
		try{
			JsonNode jsonNode = MAPPER.readTree(jsonData);
			JsonNode data = jsonNode.get("data");
			Object obj = null;
			if( data.isArray() && data.size() > 0 ){
				obj = MAPPER.readValue(data.traverse(), MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
			}
			return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
		}catch( Exception e ){
			return null;
		}
	}
}
