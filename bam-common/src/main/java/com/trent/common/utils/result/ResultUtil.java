package com.trent.common.utils.result;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.formula.functions.T;

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
	public static final Integer CODE_UPDATE_DEL_STATUS=1;
	
	private static final long serialVersionUID = -2436709332711007968L;
	
	/**
	 * 定义jackson对象
	 */
	private static final ObjectMapper MAPPER = new ObjectMapper();
	/**
	 * 响应业务状态
	 */
	private Boolean status;
	/**
	 * 响应消息
	 */
	private String msg;
	/**
	 * 响应业务状态
	 */
	private Integer code;
	/**
	 * 响应中的数据
	 */
	private Object data;
	
	public ResultUtil(String msg){
		this.status = true;
		this.msg = msg;
	}
	public ResultUtil(Object data){
		this.status = true;
		this.msg = "操作成功!";
		this.data = data;
	}
	public ResultUtil(Boolean status, String msg){
		this.status = status;
		this.msg = msg;
	}
	public ResultUtil(Boolean status, Object data){
		this.status = status;
		this.data = data;
	}
	public ResultUtil(String msg, Object data){
		this.msg = msg;
		this.data = data;
	}
	public ResultUtil(Boolean status, String msg, Object data){
		this.status = status;
		this.msg = msg;
		this.data = data;
	}
	public ResultUtil(Boolean status, String msg, Integer code, Object data){
		this.status = status;
		this.msg = msg;
		this.code = code;
		this.data = data;
	}
	public static ResultUtil ok(){
		return new ResultUtil(true,"操作成功",200);
	}
	public static ResultUtil ok(String msg){
		return new ResultUtil(msg);
	}
	public static ResultUtil ok(Object data){
		return new ResultUtil(data);
	}
	public static ResultUtil ok(Boolean status, String msg){
		return new ResultUtil(status, msg);
	}
	public static ResultUtil ok(Boolean status, Object data){
		return new ResultUtil(status, data);
	}
	public static ResultUtil ok( String msg, Object data){
		return new ResultUtil( msg, data);
	}
	public static ResultUtil ok(Boolean status, String msg, Object data){
		return new ResultUtil(status, msg, data);
	}
	public static ResultUtil ok(Boolean status, String msg, Integer code, Object data){
		return new ResultUtil(status, msg, code, data);
	}
	
	public static ResultUtil fail(String msg){
		return new ResultUtil(false,msg);
	}
	public static ResultUtil fail(Object data){
		return new ResultUtil(data);
	}
	public static ResultUtil fail(Boolean status, String msg){
		return new ResultUtil(status, msg);
		
	}
	public static ResultUtil fail(Boolean status, String msg, Object data){
		return new ResultUtil(status, msg, data);
	}
	public static ResultUtil fail(Boolean status, String msg, Integer code, Object data){
		return new ResultUtil(status, msg, code, data);
	}
}
